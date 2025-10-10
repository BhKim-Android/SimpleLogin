package com.kimbh.domain.repository

import com.kimbh.domain.model.TokenInfo

interface LoginFacebookRepository {
    suspend fun login(): Result<TokenInfo>
}