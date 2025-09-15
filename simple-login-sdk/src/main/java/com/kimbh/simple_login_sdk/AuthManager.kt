package com.kimbh.simple_login_sdk

import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType

object AuthManager {
    @Volatile
    private var authConfig: AuthConfig? = null

    fun initialize(
        authConfig: AuthConfig
    ) {
        if (this.authConfig != null) {
            throw IllegalStateException("AuthConfig has already been initialized.")
        }

        if (authConfig.appKeys.isEmpty()) {
            throw IllegalArgumentException("하나 이상의 App Key가 설정되어야 합니다.")
        }

        this.authConfig = authConfig
    }

    internal fun getAppKey(type: AuthType): String {
        ensureInitialized()
        return requireNotNull(authConfig).appKeys[type] ?: throw IllegalStateException("${type.name}의 App Key가 초기화되지 않았습니다.")
    }

    private fun ensureInitialized() {
        check(authConfig != null) { "AuthManager is not initialized. Call initialize(AuthConfig) first in Application onCreate()." }
    }
}