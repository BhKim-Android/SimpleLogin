package com.kimbh.auth_naver.di

import android.content.Context
import com.kimbh.auth_naver.client.NaverLoginClient
import com.kimbh.data.client.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object NaverLoginClientModule {

    @Provides
    @IntoSet
    fun provideNaverLoginClient(
        @ApplicationContext context: Context
    ): LoginClient {
        return NaverLoginClient(context)
    }
}