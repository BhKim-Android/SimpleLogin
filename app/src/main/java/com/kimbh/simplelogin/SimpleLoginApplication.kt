package com.kimbh.simplelogin

import android.app.Application
import com.kimbh.sdk_auth.AuthManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleLoginApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AuthManager.kakaoInit(context = this, appKey = "")
    }
}