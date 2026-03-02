package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PortfolioValueDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface PortfolioRepository {
    fun observePositions(): Flow<List<Position>>
    suspend fun refreshPositions()
    fun searchPositions(query: String): Flow<List<Position>>

    suspend fun getPortfolioValue(currency: String): DomainResult<PortfolioValueDto>
    suspend fun getPortfolioPerformance(currency: String): DomainResult<BigDecimal>
    suspend fun getCashBalance(currency: String): DomainResult<BigDecimal>
    suspend fun getReturns(currency: String): DomainResult<BigDecimal>
    suspend fun getPosition(currency: String): DomainResult<List<Position>>
}
