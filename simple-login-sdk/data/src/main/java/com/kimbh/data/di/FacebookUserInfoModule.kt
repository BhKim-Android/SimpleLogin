package com.kimbh.data.di

import com.kimbh.data.repository.UserInfoFacebookRepositoryImpl
import com.kimbh.domain.repository.UserInfoFacebookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FacebookUserInfoModule {

    @Binds
    abstract fun bindUserInfoFacebookRepository(
        userInfoFacebookRepositoryImpl: UserInfoFacebookRepositoryImpl
    ): UserInfoFacebookRepository
}