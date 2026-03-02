package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.di

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.repository.MockPortfolioRepositoryImpl
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.repository.PortfolioRepositoryImpl
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Senior Practice: Easily toggle between Mock and Real repository for development.
     * Switch back to PortfolioRepositoryImpl once API keys are restored.
     */
    @Binds
    @Singleton
    abstract fun bindPortfolioRepository(
        impl: MockPortfolioRepositoryImpl // Using Mock by default to allow UI development
    ): PortfolioRepository

}
