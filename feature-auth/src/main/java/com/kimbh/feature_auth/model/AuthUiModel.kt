package com.kimbh.feature_auth.model

import java.util.Date

data class AuthUiModel(
    val accessToken: String? = null,
    val accessTokenExpiresAt: Date? = null,
    val refreshToken: String? = null,
    val refreshTokenExpiresAt: Date? = null,
    val idToken: String? = null,
    val scopes: List<String>? = null
)
