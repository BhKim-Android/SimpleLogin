package com.kimbh.sdk_auth.di

import android.content.Context
import com.kimbh.sdk_auth.provider.AuthProvider
import com.kimbh.sdk_auth.provider.kakao.KakaoAuthProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // 앱 전역에서 하나의 인스턴스만 사용
object SdkAuthModule {

    @Provides
    @Singleton
    fun provideAuthSdk(@ApplicationContext context: Context): AuthProvider {
        // Hilt가 알아서 ApplicationContext를 주입해줍니다.
        return KakaoAuthProvider(context)
    }
}