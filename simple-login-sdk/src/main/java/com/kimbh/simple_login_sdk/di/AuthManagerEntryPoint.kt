package com.kimbh.simple_login_sdk.di

import com.kimbh.simple_login_sdk.facade.LoginHandler
import com.kimbh.simple_login_sdk.facade.SdkInitializer
import com.kimbh.simple_login_sdk.facade.UserInfoHandler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// 1. Hilt의 의존성 그래프로 들어가는 '입구'를 정의합니다.
@EntryPoint
// 2. Application 생명주기에 존재하는 SingletonComponent에 설치합니다.
@InstallIn(SingletonComponent::class)
internal interface AuthManagerEntryPoint {
    fun getSdkInitializer(): SdkInitializer
    fun getLoginHandler(): LoginHandler
    fun getUserInfoHandler(): UserInfoHandler
}