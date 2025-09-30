package com.kimbh.auth_google.strategy

import android.content.Context
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.AuthType

class GoogleInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.GOOGLE

    override fun initialize(context: Context, appKey: String) {

    }
}