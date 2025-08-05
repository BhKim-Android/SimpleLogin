package com.kimbh.simplelogin

import android.app.Application
import com.kimbh.sdk_auth.AuthManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleLoginApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Android SDK 설정 초기화.
        AuthManager.kakaoInit(context = this, appKey = getString(R.string.kakao_native_app_key))
    }
}