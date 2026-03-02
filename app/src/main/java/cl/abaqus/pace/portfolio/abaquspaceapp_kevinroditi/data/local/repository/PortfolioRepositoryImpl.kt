package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainError
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PositionDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper.toDomain
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper.toEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api.PaceApi
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PortfolioValueDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioRepositoryImpl @Inject constructor(
    private val api: PaceApi,
    private val dao: PositionDao
) : PortfolioRepository {

    override fun observePositions(): Flow<List<Position>> {
        return dao.getPositions()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun refreshPositions() {
        try {
            val positionDtos = api.getPositions("USD")
            dao.clearPositions()
            dao.insertPositions(positionDtos.map { it.toEntity() })
        } catch (e: Exception) {
            Timber.e(e, "Failed to refresh portfolio positions")
        }
    }

    override fun searchPositions(query: String): Flow<List<Position>> {
        return dao.searchPositions(query)
            .map { entities -> entities.map { it.toDomain() } }
    }

    private fun handleException(e: Exception, message: String): DomainResult.Failure {
        Timber.e(e, message)
        return when (e) {
            is HttpException -> {
                if (e.code() == 401) {
                    DomainResult.Failure(DomainError.UnauthorizedError)
                } else {
                    DomainResult.Failure(DomainError.NetworkError)
                }
            }
            else -> DomainResult.Failure(DomainError.Unknown(e))
        }
    }

    override suspend fun getPortfolioValue(currency: String): DomainResult<PortfolioValueDto> {
        return try {
            DomainResult.Success(api.getPortfolioValue(currency))
        } catch (e: Exception) {
            handleException(e, "Error fetching portfolio value")
        }
    }

    override suspend fun getPortfolioPerformance(currency: String): DomainResult<BigDecimal> {
        return try {
            DomainResult.Success(api.getPortfolioPerformance(currency).performancePercentage)
        } catch (e: Exception) {
            handleException(e, "Error fetching portfolio performance")
        }
    }

    override suspend fun getCashBalance(currency: String): DomainResult<BigDecimal> {
        return try {
            DomainResult.Success(api.getCashBalance().balance)
        } catch (e: Exception) {
            handleException(e, "Error fetching cash balance")
        }
    }

    override suspend fun getReturns(currency: String): DomainResult<BigDecimal> {
        return try {
            DomainResult.Success(api.getReturns(currency).returnsYtd)
        } catch (e: Exception) {
            handleException(e, "Error fetching returns")
        }
    }

    override suspend fun getPosition(currency: String): DomainResult<List<Position>> {
        return try {
            DomainResult.Success(api.getPositions(currency).map { it.toDomain() })
        } catch (e: Exception) {
            handleException(e, "Error fetching positions")
        }
    }
}
