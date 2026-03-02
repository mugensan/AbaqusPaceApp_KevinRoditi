package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

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
