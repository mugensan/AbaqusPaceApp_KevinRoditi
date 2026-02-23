package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto

import java.math.BigDecimal

data class PortfolioValueDto(
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val marketValue: BigDecimal,
    val performance: String
)
