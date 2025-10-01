package com.kimbh.auth_naver.config

import com.kimbh.core.utils.PlatformConfig

data class NaverAuthConfig(
    val clientId: String,
    val clientSecret: String,
    val clientName: String
) : PlatformConfig
