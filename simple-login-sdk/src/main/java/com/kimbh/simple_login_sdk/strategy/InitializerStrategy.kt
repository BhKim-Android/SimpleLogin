package com.kimbh.simple_login_sdk.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType

interface InitializerStrategy {
    val authType: AuthType
    fun initialize(context: Context, appKey: String)
}