package com.kimbh.data_remote.di

import com.kimbh.auth_google.datasource.GoogleUserInfoDataSource
import com.kimbh.data_remote.datasource.GoogleUserInfoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleUserInfoDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindGoogleUserInfoDataSource(
        googleUserInfoDataSourceImpl: GoogleUserInfoDataSourceImpl
    ): GoogleUserInfoDataSource
}