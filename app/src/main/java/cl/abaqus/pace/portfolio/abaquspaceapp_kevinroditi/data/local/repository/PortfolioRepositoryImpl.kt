package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PositionDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper.toDomain
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper.toEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api.PaceApi
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioRepositoryImpl @Inject constructor(
    private val positionDao: PositionDao,
    private val api: PaceApi
) : PortfolioRepository {

    override fun observePositions(): Flow<List<Position>> =
        positionDao.observePositions()
            .map { list -> list.map { it.toDomain() } }

    override suspend fun refreshPositions() {
        try {
            val remote = api.getPortfolioValue()
            positionDao.deleteAll()
            positionDao.insertPositions(remote.map { it.toEntity() })
        } catch (e: Exception) {
            //Fail silently -> offline-first
        }
    }

    override fun searchPositions(query: String): Flow<List<Position>> =
        positionDao.searchPositions(query)
            .map { entities -> entities.map { it.toDomain() } }
}
