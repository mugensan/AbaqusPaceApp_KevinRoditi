package com.example.wealthapp.data.remote.dto

import com.squareup.moshi.Json
import java.math.BigDecimal

data class PerformanceDto(
    @Json(name = "performance_percentage")
    val performancePercentage: BigDecimal,
    @Json(name = "period")
    val period: String
)
