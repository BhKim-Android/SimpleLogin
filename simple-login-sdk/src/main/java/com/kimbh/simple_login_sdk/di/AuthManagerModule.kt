package com.kimbh.simple_login_sdk.di

import com.kimbh.core.guard.AuthInitGuard
import com.kimbh.simple_login_sdk.AuthManager
import com.kimbh.simple_login_sdk.guard.AuthInitGuardImpl
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
object AuthManagerModule {

    @Provides
    @Singleton
    fun provideAuthManager(): AuthManager {
        return AuthManager
    }

    @Provides
    @Singleton
    fun provideAuthInitGuard(authManager: AuthManager): AuthInitGuard {
        return AuthInitGuardImpl(authManager)
    }
}