package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import javax.inject.Inject

/**
 * Use case to fetch the user portfolio
 */
class GetPortfolioUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) {
    suspend operator fun invoke(currency:String = "USD"): DomainResult<Portfolio>{
        return portfolioRepository.getPortfolio(currency)
    }
}