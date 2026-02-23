package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao {
    @Query("SELECT * FROM positions ORDER BY symbol ASC")
    fun observePositions(): Flow<List<PositionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPositions(positions: List<PositionEntity>)

    @Query("DELETE FROM positions")
    suspend fun clearPositions()

    @Query(
        """
        SELECT * FROM positions
        WHERE symbol LIKE '%' || :query || '%'
        OR name LIKE '%' || :query || '%'
    """)

    fun searchPositions(query: String): Flow<List<PositionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list:List<PositionEntity>)

    @Query("UPDATE positions SET isFavorite =NOT isFavorite WHERE symbol = :symbol")
    suspend fun toggleFavorite(symbol: String)


}
