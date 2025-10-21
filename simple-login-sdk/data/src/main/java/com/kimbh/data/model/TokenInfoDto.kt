package com.kimbh.data.model

import com.kimbh.core.utils.AuthType

data class TokenInfoDto(
    val authType: AuthType,
    val accessToken: String,
    val newToken: String
)
