package com.example.wealthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/**
 * Represents a single financial position in the local database.
 * This entity is used for caching and offline access.
 */
@Entity(tableName = "positions")
data class PositionEntity(
    @PrimaryKey
    val symbol: String,
    val name: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val marketValue: BigDecimal,
    val performance: BigDecimal,
    val isFavorite: Boolean
)
