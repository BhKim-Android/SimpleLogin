package com.kimbh.core.guard

import com.kimbh.core.utils.AuthType

interface AuthInitGuard {
    fun getAppKey(type: AuthType): String
}