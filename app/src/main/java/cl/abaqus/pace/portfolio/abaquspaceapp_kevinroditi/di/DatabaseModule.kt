package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.di

import android.content.Context
import androidx.room.Room
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PositionDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.db.PortfolioDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PortfolioDatabase =
        Room.databaseBuilder(
            context,
            PortfolioDatabase::class.java,
            "pace_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePositionDao(
        database: PortfolioDatabase
    ): PositionDao = database.positionDao()
}
