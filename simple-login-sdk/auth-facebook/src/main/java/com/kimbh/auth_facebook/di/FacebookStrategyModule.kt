package com.kimbh.auth_facebook.di

import com.kimbh.auth_facebook.strategy.FacebookInitializerStrategy
import com.kimbh.core.strategy.InitializerStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FacebookStrategyModule {
    @Provides
    @IntoSet
    fun provideFacebookInitializerStrategy(): InitializerStrategy {
        return FacebookInitializerStrategy()
    }
}