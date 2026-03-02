package com.example.wealthapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wealthapp.data.local.dao.PortfolioDao
import com.example.wealthapp.data.local.entity.PortfolioEntity
import com.example.wealthapp.data.local.entity.PositionEntity

@Database(
    entities = [PortfolioEntity::class, PositionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}
