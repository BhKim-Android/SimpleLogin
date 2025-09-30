package com.kimbh.auth_naver.di

import com.kimbh.auth_naver.strategy.NaverInitializerStrategy
import com.kimbh.core.strategy.InitializerStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object NaverStrategyModule {
    @Provides
    @IntoSet
    fun provideNaverInitializerStrategy(): InitializerStrategy {
        return NaverInitializerStrategy()
    }
}