package com.kimbh.simple_login_sdk.di

import com.kimbh.domain.usecase.FacebookLoginUsecase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
internal interface FacebookLoginEntryPoint {
    fun getLoginUsecase(): FacebookLoginUsecase
}