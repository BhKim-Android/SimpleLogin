package com.kimbh.simple_login_sdk.guard

import com.kimbh.core.guard.AuthInitGuard
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthManager
import javax.inject.Inject

class AuthInitGuardImpl @Inject constructor(
    private val authManager: AuthManager
) : AuthInitGuard {
    override fun getAppKey(type: AuthType): String {
        return authManager.getAppKey(type)
    }
}