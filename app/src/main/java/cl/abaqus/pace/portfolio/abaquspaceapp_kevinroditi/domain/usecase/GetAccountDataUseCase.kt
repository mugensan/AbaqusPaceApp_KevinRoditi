package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import javax.inject.Inject

class GetAccountDataUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    // Implement account-specific logic
}
