package com.kimbh.auth_naver.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.strategy.InitializerStrategy

class NaverInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.NAVER

    override fun initialize(context: Context, appKey: String) {
        TODO("Not yet implemented")
    }
}