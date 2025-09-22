package com.kimbh.auth_kakao.di

import com.kimbh.auth_kakao.datasource.KakaoAuthDataSourceImpl
import com.kimbh.data.datasource.KakaoAuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindKakaoAuthDataSource(
        kakaoAuthDataSourceImpl: KakaoAuthDataSourceImpl
    ): KakaoAuthDataSource
}