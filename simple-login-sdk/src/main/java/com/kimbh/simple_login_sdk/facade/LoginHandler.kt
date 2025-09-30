package com.kimbh.simple_login_sdk.facade

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.TokenInfo
import com.kimbh.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginHandler @Inject constructor(
    private val loginUseCase: LoginUseCase
) {
    suspend fun login(authType: AuthType): Result<TokenInfo> {
        return loginUseCase(authType = authType)
    }
}