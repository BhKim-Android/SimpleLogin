package com.kimbh.auth_google.di

import com.kimbh.auth_google.client.GoogleUserInfoClient
import com.kimbh.data.client.UserInfoClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleUserInfoModule {
    @Binds
    @Singleton
    abstract fun bindGoogleUserInfoClient(
        googleUserInfoClient: GoogleUserInfoClient
    ): UserInfoClient
}