package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import javax.inject.Inject

class GetTradeDataUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    // Implement trade-specific logic here
}
