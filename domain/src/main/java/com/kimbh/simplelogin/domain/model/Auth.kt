package com.kimbh.simplelogin.domain.model

import java.util.Date

data class Auth(
    val accessToken: String,
    val accessTokenExpiresAt: Date,
    val refreshToken: String,
    val refreshTokenExpiresAt: Date,
    val idToken: String? = null,
    val scopes: List<String>? = null
)
