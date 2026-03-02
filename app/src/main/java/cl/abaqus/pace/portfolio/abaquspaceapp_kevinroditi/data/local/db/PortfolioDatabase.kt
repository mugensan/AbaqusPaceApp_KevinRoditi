package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PositionDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity

@Database(
    entities = [PositionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PortfolioDatabase : RoomDatabase() {
    abstract fun positionDao(): PositionDao
}
