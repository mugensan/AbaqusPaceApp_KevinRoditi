package com.example.wealthapp.data.repository

import com.example.wealthapp.core.network.Resource
import com.example.wealthapp.data.local.dao.PortfolioDao
import com.example.wealthapp.data.local.entity.PortfolioEntity
import com.example.wealthapp.data.mapper.toDomain
import com.example.wealthapp.data.mapper.toEntity
import com.example.wealthapp.data.remote.api.WealthApi
import com.example.wealthapp.domain.model.Portfolio
import com.example.wealthapp.domain.model.Position
import com.example.wealthapp.domain.repository.WealthRepository
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WealthRepositoryImpl @Inject constructor(
    private val api: WealthApi,
    private val dao: PortfolioDao
) : WealthRepository {

    override fun getPortfolio(): Flow<Resource<Portfolio>> = flow {
        emit(Resource.Loading())

        // Collect from DB and emit immediately as cached data
        val cachedData = dao.getPortfolio().firstOrNull()?.toDomain()
        if (cachedData != null) {
            emit(Resource.Success(cachedData))
        }

        // We also want to keep observing the DB
        dao.getPortfolio()
            .filterNotNull()
            .map { Resource.Success(it.toDomain()) }
            .collect { emit(it) }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "An unknown error occurred"))
    }

    override fun getPositions(): Flow<Resource<List<Position>>> = flow {
        emit(Resource.Loading())

        // Initial emission from DB
        val cachedPositions = dao.getPositions().firstOrNull()?.map { it.toDomain() }
        if (!cachedPositions.isNullOrEmpty()) {
            emit(Resource.Success(cachedPositions))
        }

        // Continuous observation
        dao.getPositions()
            .map { entities -> Resource.Success(entities.map { it.toDomain() }) }
            .collect { emit(it) }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "An unknown error occurred"))
    }

    override suspend fun refreshPortfolio() {
        try {
            // Fetch all summary components in parallel or sequence
            // For production-grade, we use sequence or async for complex cases.
            val valueDto = api.getPortfolioValue()
            val performanceDto = api.getPortfolioPerformance()
            val cashDto = api.getCashBalance()
            val returnsDto = api.getPortfolioReturns()
            val positionsDto = api.getPositions()

            val portfolioEntity = PortfolioEntity(
                totalValue = valueDto.totalValue,
                performancePercentage = performanceDto.performancePercentage,
                cashBalance = cashDto.balance,
                returnsYtd = returnsDto.performancePercentage
            )

            // Atomic update of cache
            dao.insertPortfolio(portfolioEntity)
            dao.insertPositions(positionsDto.map { it.toEntity() })

        } catch (e: Exception) {
            Timber.e(e, "Failed to refresh portfolio")
            throw e // Re-throw so use case/viewmodel can handle if needed
        }
    }

    override fun searchPositions(query: String): Flow<List<Position>> {
        return dao.getPositions()
            .map { entities ->
                entities.map { it.toDomain() }.filter { 
                    it.symbol.contains(query, ignoreCase = true) || 
                    it.name.contains(query, ignoreCase = true) 
                }
            }
    }
}
