package com.kimbh.simplelogin

import android.app.Application
import com.kimbh.core.config.FacebookAuthConfig
import com.kimbh.core.config.GoogleAuthConfig
import com.kimbh.core.config.KakaoAuthConfig
import com.kimbh.core.config.NaverAuthConfig
import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleLoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Android SDK 설정 초기화.
        val authConfig = AuthConfig(
            platformConfigs = mapOf(
                AuthType.KAKAO to KakaoAuthConfig(appkey = "821cb821d1ae2781c9839f9e390421f7"),
                AuthType.NAVER to NaverAuthConfig(
                    clientId = "TabvDQibhR6yQTWfL2NW",
                    clientSecret = "U6kNse4Lfr",
                    clientName = "kimbhSimpleLogin"
                ),
                AuthType.FACEBOOK to FacebookAuthConfig,
                AuthType.GOOGLE to GoogleAuthConfig(serverClientId = "")
            )
        )
        AuthManager.initialize(context = this, authConfig = authConfig)
    }
}