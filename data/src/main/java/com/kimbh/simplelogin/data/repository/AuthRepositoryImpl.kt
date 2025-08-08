package com.kimbh.simplelogin.data.repository

import com.kimbh.core.common.AuthResult
import com.kimbh.sdk_auth.model.KakaoResponse
import com.kimbh.sdk_auth.provider.kakao.KakaoAuthProvider
import com.kimbh.simplelogin.domain.model.Auth
import com.kimbh.simplelogin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val kakaoAuthProvider: KakaoAuthProvider
) : AuthRepository {

    override fun login(callback: (AuthResult<Auth>) -> Unit) {
        kakaoAuthProvider.login { kakaoResult ->
            when (kakaoResult) {
                is AuthResult.Success -> {
                    val domainAuth = mapToDomain(kakaoResult.data)
                    callback(AuthResult.Success(domainAuth))
                }

                is AuthResult.Error -> {
                    callback(AuthResult.Error(kakaoResult.exception))
                }
            }
        }
    }

    private fun mapToDomain(kakaoResponse: KakaoResponse): Auth =
        Auth(token = kakaoResponse.accessToken)

}