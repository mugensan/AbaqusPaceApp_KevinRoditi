package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.use_case

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPositionsUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    operator fun invoke(query: String): Flow<List<Position>> {
        return repository.searchPositions(query)
    }
}
