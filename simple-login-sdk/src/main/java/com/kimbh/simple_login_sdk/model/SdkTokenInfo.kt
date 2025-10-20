package com.kimbh.simple_login_sdk.model

import com.kimbh.core.utils.AuthType

data class SdkTokenInfo(
    val authType: AuthType,
    val accessToken: String,
    val newToken: String
)
