package com.kimbh.sdk_auth.model

import java.util.Date

data class KakaoResponse(
    val accessToken: String,
    val accessTokenExpiresAt: Date,
    val refreshToken: String,
    val refreshTokenExpiresAt: Date,
    val idToken: String? = null,
    val scopes: List<String>? = null
)
