package com.kimbh.auth_google.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.strategy.InitializerStrategy

class GoogleInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.GOOGLE

    override fun initialize(context: Context, appKey: String) {

    }
}