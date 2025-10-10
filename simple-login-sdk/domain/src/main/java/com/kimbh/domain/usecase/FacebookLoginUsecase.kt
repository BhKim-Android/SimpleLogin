package com.kimbh.domain.usecase

import com.kimbh.domain.model.TokenInfo
import com.kimbh.domain.repository.LoginFacebookRepository
import javax.inject.Inject

class FacebookLoginUsecase @Inject constructor(
    private val loginFacebookRepository: LoginFacebookRepository
) {
    suspend operator fun invoke(): Result<TokenInfo> {
        return loginFacebookRepository.login()
    }
}