package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.repository

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PortfolioValueDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockPortfolioRepositoryImpl @Inject constructor() : PortfolioRepository {

    override fun observePositions(): Flow<List<Position>> = flowOf(getMockPositions())

    override suspend fun refreshPositions() {
        delay(500)
    }

    override fun searchPositions(query: String): Flow<List<Position>> = flowOf(
        getMockPositions().filter {
            it.symbol.contains(query, ignoreCase = true) || it.name.contains(query, ignoreCase = true)
        }
    )

    override suspend fun getPortfolioValue(currency: String): DomainResult<PortfolioValueDto> {
        delay(500)
        return DomainResult.Success(PortfolioValueDto(totalValue = BigDecimal("40433.00"), currency = currency))
    }

    override suspend fun getPortfolioPerformance(currency: String): DomainResult<BigDecimal> {
        delay(500)
        return DomainResult.Success(BigDecimal("0.04"))
    }

    override suspend fun getCashBalance(currency: String): DomainResult<BigDecimal> {
        delay(500)
        return DomainResult.Success(BigDecimal("40455.21"))
    }

    override suspend fun getReturns(currency: String): DomainResult<BigDecimal> {
        delay(500)
        return DomainResult.Success(BigDecimal("7.06"))
    }

    override suspend fun getPosition(currency: String): DomainResult<List<Position>> {
        delay(500)
        return DomainResult.Success(getMockPositions())
    }

    private fun getMockPositions() = listOf(
        Position("AAL", "American Airlines Group Inc.", BigDecimal("100"), BigDecimal("396.84"), BigDecimal("396.84"), BigDecimal("1.2"), false),
        Position("AMZN", "Amazon", BigDecimal("5"), BigDecimal("854.08"), BigDecimal("854.08"), BigDecimal("-0.5"), true),
        Position("TSLA", "Tesla", BigDecimal("10"), BigDecimal("576.28"), BigDecimal("576.28"), BigDecimal("2.1"), false),
        Position("AAPL", "Apple", BigDecimal("15"), BigDecimal("782.01"), BigDecimal("782.01"), BigDecimal("0.8"), true),
        Position("EXPI", "EXP World Holdings, Inc.", BigDecimal("50"), BigDecimal("219.78"), BigDecimal("219.78"), BigDecimal("-1.2"), false)
    )
}
