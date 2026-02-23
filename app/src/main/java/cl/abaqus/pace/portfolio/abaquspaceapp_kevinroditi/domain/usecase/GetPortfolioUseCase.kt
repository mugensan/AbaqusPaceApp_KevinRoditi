package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainError
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.math.BigDecimal
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

                val results = listOf(
                    valueResult,
                    performanceResult,
                    cashBalanceResult,
                    returnsResult,
                    positionsResult
                )

                val firstFailure = results.firstOrNull { it is DomainResult.Failure }
                if (firstFailure != null) {
                    return@coroutineScope firstFailure as DomainResult.Failure
                }

                // Safely extract data, assuming some inconsistencies in repository return types
                val totalValue = (valueResult as DomainResult.Success).data.totalValue
                val performanceMap = (performanceResult as DomainResult.Success)
                    .data as Map<String, String>
                val performanceYtd = BigDecimal(performanceMap["performanceYdt"] ?: "0.0")

                DomainResult.Success(
                    Portfolio(
                        totalValue = totalValue,
                        performanceYdt = performanceYtd,
                        cashBalance = (cashBalanceResult as DomainResult.Success).data,
                        returnsYdt = (returnsResult as DomainResult.Success).data,
                        positions = (positionsResult as DomainResult.Success).data
                    )
                )
            } catch (e: Exception) {
                DomainResult.Failure(DomainError.Unknown(e))
            }
        }
}