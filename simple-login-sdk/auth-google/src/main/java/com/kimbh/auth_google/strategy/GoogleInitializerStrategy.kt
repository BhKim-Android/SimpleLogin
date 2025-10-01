package com.kimbh.auth_google.strategy

import android.content.Context
import com.kimbh.auth_google.GoogleAuth
import com.kimbh.core.config.GoogleAuthConfig
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.AuthType
import com.kimbh.core.utils.PlatformConfig

class GoogleInitializerStrategy : InitializerStrategy {
    override val authType: AuthType
        get() = AuthType.GOOGLE

    override fun initialize(
        context: Context,
        platformConfig: PlatformConfig
    ) {
        if (platformConfig is GoogleAuthConfig) {
            GoogleAuth.init(serverClientId = platformConfig.serverClientId)
        }
    }
}