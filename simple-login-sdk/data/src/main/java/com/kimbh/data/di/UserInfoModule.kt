package com.kimbh.data.di

import com.kimbh.data.repository.UserInfoRepositoryImpl
import com.kimbh.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserInfoModule {
    @Singleton
    @Binds
    abstract fun bindsUserInfoRepository(userInfoRepositoryImpl: UserInfoRepositoryImpl): UserInfoRepository
}