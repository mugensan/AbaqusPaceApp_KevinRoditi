package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.di

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.dao.PositionDao
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.db.PortfolioDatabase
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule (
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context:Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pace_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providePortfolioDao(
        database: PortfolioDatabase
    ): PositionDao = database.positionDao()

    @Provides
    fun providePositionDao(
        database: PortfolioDatabase
    ): PositionDao = database.positionDao()
)

