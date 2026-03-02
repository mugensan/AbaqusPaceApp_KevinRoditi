package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model

import java.math.BigDecimal

data class Portfolio(
    val balance: BigDecimal,
    val performance: BigDecimal,
    val value: BigDecimal
)
