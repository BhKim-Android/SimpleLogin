package com.kimbh.data.di

import com.kimbh.data.repository.LoginFacebookRepositoryImpl
import com.kimbh.domain.repository.LoginFacebookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class FacebookLoginModule {
    @Binds
    abstract fun bindLoginFacebookRepository(
        loginFacebookRepositoryImpl: LoginFacebookRepositoryImpl
    ): LoginFacebookRepository
}