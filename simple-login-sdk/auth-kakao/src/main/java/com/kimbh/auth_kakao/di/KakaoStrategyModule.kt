package com.kimbh.auth_kakao.di

import com.kimbh.auth_kakao.strategy.KakaoInitializerStrategy
import com.kimbh.simple_login_sdk.strategy.InitializerStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KakaoStrategyModule {

    @Provides
    @Singleton
    @IntoSet
    fun provideKakaoInitializerStrategy(): InitializerStrategy {
        return KakaoInitializerStrategy()
    }
}