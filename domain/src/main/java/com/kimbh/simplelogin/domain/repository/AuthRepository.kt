package com.kimbh.simplelogin.domain.repository

import com.kimbh.core.common.AuthResult
import com.kimbh.simplelogin.domain.model.Auth

interface AuthRepository {
    suspend fun login(): AuthResult<Auth>
}