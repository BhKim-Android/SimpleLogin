package com.kimbh.auth_naver.strategy

import android.content.Context
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.AuthType

class NaverInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.NAVER

    override fun initialize(context: Context, appKey: String) {
        TODO("Not yet implemented")
    }
}