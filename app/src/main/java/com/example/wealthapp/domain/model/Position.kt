package com.example.wealthapp.domain.model

import java.math.BigDecimal

/**
 * Pure domain model representing a financial position.
 */
data class Position(
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val marketValue: BigDecimal,
    val performance: BigDecimal,
    val isFavorite: Boolean
)
