package com.kimbh.auth_kakao.client

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.data.model.TokenInfoDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class KakaoLoginClient @Inject constructor(
    @param:ApplicationContext val context: Context
) : LoginClient {
    override val authType: AuthType
        get() = AuthType.KAKAO

    override suspend fun login(): Result<TokenInfoDto> = runCatching {
        val token = loginWithKakao()
        TokenInfoDto(
            authType = authType,
            accessToken = token.accessToken,
            newToken = token.refreshToken
        )
    }

    /**
     * 카카오톡 로그인
     * @return OAuthToken : 카카오 토큰 정보를 담고 있는 data class
     **/
    private suspend fun loginWithKakao(): OAuthToken {
        return if (UserApiClient.instance.isKakaoTalkLoginAvailable(context = context)) {
            // 카카오톡이 설치되어 있는 경우..
            try {
                loginWithKakaoTalk()
            } catch (e: Exception) {
                if (e is ClientError && e.reason == ClientErrorCause.Cancelled) {
                    loginWithKakaoAccount()
                } else {
                    throw e
                }
            }
        } else {
            // 카카오톡이 없어 웹으로 요청한 경우..
            loginWithKakaoAccount()
        }
    }

    private suspend fun loginWithKakaoTalk(): OAuthToken {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoTalk(context = context) { token, error ->
                if (error != null) {
                    continuation.resumeWith(Result.failure(error))
                } else if (token != null) {
                    continuation.resume(token)
                } else {
                    continuation.resumeWith(Result.failure(Throwable("KakaoTalk login failed without error.")))
                }
            }
        }
    }

    private suspend fun loginWithKakaoAccount(): OAuthToken {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoAccount(context = context) { token, error ->
                if (error != null) {
                    continuation.resumeWith(Result.failure(error))
                } else if (token != null) {
                    continuation.resume(token)
                } else {
                    continuation.resumeWith(Result.failure(Throwable("KakaoTalk login failed without error.")))
                }
            }
        }
    }
}