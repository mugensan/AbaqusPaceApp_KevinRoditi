package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model

import java.math.BigDecimal


/**
 * Domain model rep. a user portfolio
 */

data class Portfolio(
    val totalValue: BigDecimal,
    val performanceYdt: BigDecimal,
    val cashBalance: BigDecimal,
    val returnsYdt: BigDecimal,
    val positions: List<Position>
)