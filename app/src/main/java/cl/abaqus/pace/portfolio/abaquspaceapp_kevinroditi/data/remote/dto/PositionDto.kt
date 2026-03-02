package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto

import com.squareup.moshi.Json
import java.math.BigDecimal

data class PositionDto(
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "quantity")
    val quantity: BigDecimal,
    @Json(name = "price")
    val price: BigDecimal,
    @Json(name = "market_value")
    val marketValue: BigDecimal,
    @Json(name = "performance")
    val performance: BigDecimal,
    @Json(name = "is_favorite")
    val isFavorite: Boolean
)
