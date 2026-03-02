package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.network.Resource
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import kotlinx.coroutines.flow.Flow

interface WealthRepository {
    fun getPortfolio(): Flow<Resource<Portfolio>>
    fun getPositions(): Flow<Resource<List<Position>>>
    suspend fun refreshPortfolio()
    fun searchPositions(query: String): Flow<List<Position>>
}
