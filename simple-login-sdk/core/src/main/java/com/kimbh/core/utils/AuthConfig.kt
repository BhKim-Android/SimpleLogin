package com.kimbh.core.utils

data class AuthConfig(
    val platformConfigs: Map<AuthType, PlatformConfig>
)
