package com.kimbh.simplelogin.domain

import com.kimbh.simplelogin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUsecase @Inject constructor(
    private val authRepository: AuthRepository
) {
     fun login() {

    }
}