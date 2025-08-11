package com.kimbh.simplelogin.domain

import com.kimbh.core.common.AuthResult
import com.kimbh.simplelogin.domain.model.Auth
import com.kimbh.simplelogin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUsecase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator suspend fun invoke(): AuthResult<Auth> {
        return authRepository.login()
    }
}