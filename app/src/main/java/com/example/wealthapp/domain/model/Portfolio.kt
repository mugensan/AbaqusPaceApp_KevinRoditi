package com.example.wealthapp.domain.model

import java.math.BigDecimal

/**
 * Pure domain model representing the portfolio summary.
 */
data class Portfolio(
    val totalValue: BigDecimal,
    val performancePercentage: BigDecimal,
    val cashBalance: BigDecimal,
    val returnsYtd: BigDecimal
)
