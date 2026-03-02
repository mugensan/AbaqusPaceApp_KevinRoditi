package com.example.wealthapp.domain.usecase

import com.example.wealthapp.core.network.Resource
import com.example.wealthapp.domain.repository.WealthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject

class GetPortfolioValueUseCase @Inject constructor(
    private val repository: WealthRepository
) {
    operator fun invoke(): Flow<Resource<BigDecimal>> = repository.getPortfolio().map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(resource.data!!.totalValue)
            is Resource.Error -> Resource.Error(resource.message!!, resource.data?.totalValue)
            is Resource.Loading -> Resource.Loading(resource.data?.totalValue)
        }
    }
}
