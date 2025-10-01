package com.kimbh.auth_naver.strategy

import android.content.Context
import com.kimbh.core.config.NaverAuthConfig
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.AuthType
import com.kimbh.core.utils.PlatformConfig
import com.navercorp.nid.NaverIdLoginSDK

class NaverInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.NAVER

    override fun initialize(
        context: Context,
        platformConfig: PlatformConfig
    ) {
        if (platformConfig is NaverAuthConfig) {
            NaverIdLoginSDK.initialize(
                context = context,
                clientId = platformConfig.clientId,
                clientSecret = platformConfig.clientSecret,
                clientName = platformConfig.clientName
            )
        } else {
            throw Throwable("NaverInitializer: Invalid config type provided.")
        }
    }
}