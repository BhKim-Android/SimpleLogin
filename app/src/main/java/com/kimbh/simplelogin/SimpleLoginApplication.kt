package com.kimbh.simplelogin

import android.app.Application
import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleLoginApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Android SDK 설정 초기화.
        val authType = AuthType.KAKAO
        val authConfig = AuthConfig(appKeys = mapOf(authType to "821cb821d1ae2781c9839f9e390421f7"))
        AuthManager.initialize(context = this, authConfig = authConfig)
    }
}