package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model

import java.math.BigDecimal

data class Position(
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val marketValue: BigDecimal,
    val performance: BigDecimal,
    val isFavorite: Boolean
)
