package com.kimbh.auth_kakao.di

import com.kimbh.auth_kakao.client.KakaoUserInfoClientImpl
import com.kimbh.data.client.UserInfoClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoClientModule {
    @Provides
    @Singleton
    @IntoSet
    fun provideKakaoUserInfoClient(): UserInfoClient {
        return KakaoUserInfoClientImpl()
    }
}