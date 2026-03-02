package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PortfolioDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PortfolioEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity

@Database(
    entities = [PortfolioEntity::class, PositionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}
