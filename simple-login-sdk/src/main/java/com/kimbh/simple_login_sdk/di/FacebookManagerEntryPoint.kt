package com.kimbh.simple_login_sdk.di

import com.kimbh.domain.usecase.FacebookUserInfoUsecase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FacebookManagerEntryPoint {
    fun getUserInfoUsecase(): FacebookUserInfoUsecase
}