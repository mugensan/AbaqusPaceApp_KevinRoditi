package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto

import com.squareup.moshi.Json
import java.math.BigDecimal

data class ReturnsDto(
    @Json(name = "returns_ytd")
    val returnsYtd: BigDecimal,
    @Json(name = "currency")
    val currency: String
)
