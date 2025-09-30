package com.kimbh.auth_kakao.di

import com.kimbh.auth_kakao.strategy.KakaoInitializerStrategy
import com.kimbh.core.strategy.InitializerStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object KakaoStrategyModule {
    @Provides
    @IntoSet
    fun provideKakaoInitializerStrategy(): InitializerStrategy {
        return KakaoInitializerStrategy()
    }
}