package com.example.wealthapp.domain.usecase

import com.example.wealthapp.core.network.Resource
import com.example.wealthapp.domain.model.Position
import com.example.wealthapp.domain.repository.WealthRepository
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
