package com.kimbh.domain.usecase

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.TokenInfo
import com.kimbh.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(authType: AuthType): Result<TokenInfo> {
        return loginRepository.login(authType = authType)
    }
}