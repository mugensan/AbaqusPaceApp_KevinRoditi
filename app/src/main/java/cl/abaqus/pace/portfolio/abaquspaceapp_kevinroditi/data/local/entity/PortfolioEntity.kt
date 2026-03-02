package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "portfolio")
data class PortfolioEntity(
    @PrimaryKey
    val id: Int = 0,
    val totalValue: BigDecimal,
    val performancePercentage: BigDecimal,
    val cashBalance: BigDecimal,
    val returnsYtd: BigDecimal
)
