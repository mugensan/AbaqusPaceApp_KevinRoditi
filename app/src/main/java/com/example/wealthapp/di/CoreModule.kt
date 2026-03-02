package com.example.wealthapp.di

import com.example.wealthapp.core.utils.AppLogger
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.loggin.TimberLogger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindLogger(timberLogger: TimberLogger): AppLogger
}
