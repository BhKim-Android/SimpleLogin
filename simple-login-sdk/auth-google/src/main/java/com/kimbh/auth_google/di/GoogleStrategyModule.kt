package com.kimbh.auth_google.di

import com.kimbh.auth_google.strategy.GoogleInitializerStrategy
import com.kimbh.core.strategy.InitializerStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object GoogleStrategyModule {
    @Provides
    @IntoSet
    fun provideGoogleInitializerStrategy(): InitializerStrategy {
        return GoogleInitializerStrategy()
    }
}