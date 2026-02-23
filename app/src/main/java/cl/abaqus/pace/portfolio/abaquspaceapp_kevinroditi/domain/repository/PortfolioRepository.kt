package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun observePositions(): Flow<List<Position>>
    suspend fun refreshPositions()
    fun searchPositions(query: String): Flow<List<Position>>
}
