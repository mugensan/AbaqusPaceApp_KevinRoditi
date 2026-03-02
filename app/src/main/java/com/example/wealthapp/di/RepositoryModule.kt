package com.example.wealthapp.di

import com.example.wealthapp.data.repository.WealthRepositoryImpl
import com.example.wealthapp.domain.repository.WealthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWealthRepository(
        wealthRepositoryImpl: WealthRepositoryImpl
    ): WealthRepository
}
