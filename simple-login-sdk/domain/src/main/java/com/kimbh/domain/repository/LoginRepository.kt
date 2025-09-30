package com.kimbh.domain.repository

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.TokenInfo

interface LoginRepository {
    suspend fun login(authType: AuthType): Result<TokenInfo>
}