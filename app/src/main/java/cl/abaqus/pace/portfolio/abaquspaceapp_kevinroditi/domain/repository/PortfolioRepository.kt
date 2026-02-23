package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import java.math.BigDecimal

interface PortfolioRepository {
    suspend fun getPortfolioValue(currency: String = "USD"):
            DomainResult<Portfolio>
    suspend fun getPortfolioPerformance(currency: String = "USD"):
            DomainResult<Map<String,String>>
    suspend fun getCashBalance(currency: String = "USD"):
            DomainResult<BigDecimal>
    suspend fun getReturns(currency: String = "USD"):
            DomainResult<BigDecimal>
    suspend fun getPosition(currency: String = "USD"):
            DomainResult<List<Position>>
    suspend fun searchAssets(query: String):
            DomainResult<List<Position>>
}