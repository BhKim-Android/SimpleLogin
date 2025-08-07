package com.kimbh.simplelogin.data.repository

import com.kimbh.core.common.AuthResult
import com.kimbh.sdk_auth.provider.kakao.KakaoAuthProvider
import com.kimbh.simplelogin.domain.model.Auth
import com.kimbh.simplelogin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val kakaoAuthProvider: KakaoAuthProvider
) : AuthRepository {

    override fun login(): AuthResult<Auth> {

    }

}