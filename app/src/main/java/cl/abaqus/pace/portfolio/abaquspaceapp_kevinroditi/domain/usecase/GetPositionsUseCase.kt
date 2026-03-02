package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.network.Resource
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.WealthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val repository: WealthRepository
) {
    operator fun invoke(): Flow<Resource<List<Position>>> {
        return repository.getPositions()
    }

    suspend fun refresh() {
        repository.refreshPortfolio()
    }
}
