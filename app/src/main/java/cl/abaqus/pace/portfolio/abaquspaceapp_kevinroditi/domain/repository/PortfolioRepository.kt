package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position

interface PortfolioRepository {
    suspend fun getPortfolio(currency: String = "USD"): DomainResult<Portfolio>
    suspend fun searchAssets(query: String): DomainResult<List<Position>>
}