package com.kimbh.auth_facebook.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.strategy.InitializerStrategy

class FacebookInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.FACEBOOK

    override fun initialize(context: Context, appKey: String) {

    }
}