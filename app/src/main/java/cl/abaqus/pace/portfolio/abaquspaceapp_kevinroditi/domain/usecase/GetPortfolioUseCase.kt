package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainError
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * Use case to fetch the user portfolio
 */
class GetPortfolioUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) {
    suspend fun execute(currency: String = "USD"): DomainResult<Portfolio> =
        coroutineScope {
            try {
                val valueDeferred = async { portfolioRepository.getPortfolioValue(currency) }
                val performanceDeferred = async { portfolioRepository.getPortfolioPerformance(currency) }
                val cashBalanceDeferred = async { portfolioRepository.getCashBalance(currency) }
                val returnsDeferred = async { portfolioRepository.getReturns(currency) }
                val positionsDeferred = async { portfolioRepository.getPosition(currency) }

                val valueResult = valueDeferred.await()
                val performanceResult = performanceDeferred.await()
                val cashBalanceResult = cashBalanceDeferred.await()
                val returnsResult = returnsDeferred.await()
                val positionsResult = positionsDeferred.await()

                if (valueResult is DomainResult.Failure) return@coroutineScope valueResult
                if (performanceResult is DomainResult.Failure) return@coroutineScope performanceResult
                if (cashBalanceResult is DomainResult.Failure) return@coroutineScope cashBalanceResult
                if (returnsResult is DomainResult.Failure) return@coroutineScope returnsResult
                if (positionsResult is DomainResult.Failure) return@coroutineScope positionsResult

                DomainResult.Success(
                    Portfolio(
                        totalValue = (valueResult as DomainResult.Success).data.totalValue,
                        performancePercentage = (performanceResult as DomainResult.Success).data,
                        cashBalance = (cashBalanceResult as DomainResult.Success).data,
                        returnsYtd = (returnsResult as DomainResult.Success).data,
                        positions = (positionsResult as DomainResult.Success).data
                    )
                )
            } catch (e: Exception) {
                DomainResult.Failure(DomainError.Unknown(e))
            }
        }
}
