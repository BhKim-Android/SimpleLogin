package com.kimbh.auth_kakao.di

import android.content.Context
import com.kimbh.auth_kakao.client.KakaoLoginClient
import com.kimbh.data.client.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object KakaoLoginClientModule {

    @Provides
    @IntoSet
    fun provideKakaoLoginClient(
        @ApplicationContext context: Context
    ): LoginClient {
        return KakaoLoginClient(context)
    }
}