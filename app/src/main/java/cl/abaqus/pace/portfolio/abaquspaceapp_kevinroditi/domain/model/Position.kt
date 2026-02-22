package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model

import java.math.BigDecimal

data class Position(
    val name: String,
    val symbol: String,
    val marketValue: BigDecimal
)