package com.kimbh.auth_facebook.strategy

import android.content.Context
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.AuthType
import com.kimbh.core.utils.PlatformConfig
import javax.inject.Inject

class FacebookInitializerStrategy @Inject constructor(

) : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.FACEBOOK

    override fun initialize(
        context: Context,
        platformConfig: PlatformConfig
    ) {
        // 페이스북은 자동 초기화..

    }

}