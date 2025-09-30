package com.kimbh.data.client

import com.kimbh.core.utils.AuthType
import com.kimbh.data.model.TokenInfoDto

interface LoginClient {
    val authType: AuthType
    suspend fun login(): Result<TokenInfoDto>
}