package com.example.wealthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/**
 * Represents the overall portfolio status in the local database.
 */
@Entity(tableName = "portfolio")
data class PortfolioEntity(
    @PrimaryKey
    val id: Int = 0, // Only one portfolio summary needed
    val totalValue: BigDecimal,
    val performancePercentage: BigDecimal,
    val cashBalance: BigDecimal,
    val returnsYtd: BigDecimal
)
