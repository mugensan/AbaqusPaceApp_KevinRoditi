package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.use_case

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    operator fun invoke(): Flow<Pair<List<Position>, List<Position>>> = repository.observePositions()
        .combine(repository.observePositions()) { positions, searchResults ->
            Pair(positions, searchResults)
        }

    suspend fun refresh() {
        repository.refreshPositions()
    }
}
