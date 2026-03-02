package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PortfolioEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolio(portfolio: PortfolioEntity)

    @Query("SELECT * FROM portfolio WHERE id = 0")
    fun getPortfolio(): Flow<PortfolioEntity?>

    @Query("DELETE FROM portfolio")
    suspend fun clearPortfolio()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPositions(positions: List<PositionEntity>)

    @Query("SELECT * FROM positions")
    fun getPositions(): Flow<List<PositionEntity>>

    @Query("DELETE FROM positions")
    suspend fun clearPositions()
}
