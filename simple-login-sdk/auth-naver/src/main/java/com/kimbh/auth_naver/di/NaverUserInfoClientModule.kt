package com.kimbh.auth_naver.di

import com.kimbh.auth_naver.client.NaverUserInfoClient
import com.kimbh.data.client.UserInfoClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NaverUserInfoClientModule {
    @Singleton
    @Provides
    @IntoSet
    fun provideNaverUserInfoClient(): UserInfoClient {
        return NaverUserInfoClient()
    }
}