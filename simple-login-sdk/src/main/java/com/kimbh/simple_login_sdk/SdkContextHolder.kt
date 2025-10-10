package com.kimbh.simple_login_sdk

import android.content.Context

internal object SdkContextHolder {
    private lateinit var applicationContext: Context

    fun initialize(context: Context) {
        if (!::applicationContext.isInitialized) {
            applicationContext = context.applicationContext
        }
    }

    fun getApplicationContext(): Context {
        if (!::applicationContext.isInitialized) throw IllegalStateException("SDK is not initialized")
        return applicationContext
    }
}