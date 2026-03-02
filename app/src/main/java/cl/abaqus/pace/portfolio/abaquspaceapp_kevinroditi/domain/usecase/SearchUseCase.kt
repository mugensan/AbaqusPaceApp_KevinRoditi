package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import javax.inject.Inject

/**
 * Use case to search assets by name or symbol
 */
class SearchUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    operator fun invoke(query: String) = repository.searchPositions(query)
}
