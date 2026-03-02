package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto

import com.squareup.moshi.Json
import java.math.BigDecimal

data class CashBalanceDto(
    @Json(name = "balance")
    val balance: BigDecimal,
    @Json(name = "currency")
    val currency: String
)
