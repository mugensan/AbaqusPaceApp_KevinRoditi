package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto

import com.squareup.moshi.Json
import java.math.BigDecimal

data class PortfolioValueDto(
    @Json(name = "total_value")
    val totalValue: BigDecimal,
    @Json(name = "currency")
    val currency: String
)
