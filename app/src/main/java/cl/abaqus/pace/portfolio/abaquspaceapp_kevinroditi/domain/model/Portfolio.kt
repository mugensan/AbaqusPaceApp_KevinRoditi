package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model

import java.math.BigDecimal

data class Portfolio(
    val totalValue: BigDecimal,
    val performancePercentage: BigDecimal,
    val cashBalance: BigDecimal,
    val returnsYtd: BigDecimal,
    val positions: List<Position>
)
