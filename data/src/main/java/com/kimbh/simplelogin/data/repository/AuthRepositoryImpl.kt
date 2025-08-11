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

    override suspend fun login(): AuthResult<Auth> =
        try {
            AuthResult.Success(data = mapToAuth(kakaoAuthProvider.login()))
        } catch (e: Exception) {
            AuthResult.Error(exception = e)
        }

    private fun mapToAuth(kakaoResponse: KakaoResponse): Auth =
        Auth(
            accessToken = kakaoResponse.accessToken,
            accessTokenExpiresAt = kakaoResponse.accessTokenExpiresAt,
            refreshToken = kakaoResponse.refreshToken,
            refreshTokenExpiresAt = kakaoResponse.refreshTokenExpiresAt,
            idToken = kakaoResponse.idToken,
            scopes = kakaoResponse.scopes
        )
}