package com.kimbh.auth_facebook.di

import com.kimbh.auth_facebook.client.FacebookUserInfoClient
import com.kimbh.data.client.UserInfoClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FacebookUserInfoClientModule {
    @Provides
    @Singleton
    fun provideFacebookUserInfoClient(): UserInfoClient = FacebookUserInfoClient()
}