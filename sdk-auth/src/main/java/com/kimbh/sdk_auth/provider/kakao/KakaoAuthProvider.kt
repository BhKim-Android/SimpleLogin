package com.kimbh.sdk_auth.provider.kakao

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kimbh.sdk_auth.model.KakaoResponse
import com.kimbh.sdk_auth.provider.AuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


/**
 * suspendCancellableCoroutine 적용하기!!!!!
 **/
class KakaoAuthProvider(private val context: Context) : AuthProvider {

    override suspend fun login(): KakaoResponse {
        return suspendCancellableCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                // continuation이 아직 활성 상태일 때만 재개하도록 보장합니다.
                if (continuation.isActive) {
                    if (error != null) {
                        continuation.resumeWithException(error)
                    } else if (token != null) {
                        continuation.resume(mapToKakaoResponse(token))
                    } else {
                        continuation.resumeWithException(RuntimeException("Kakao login failed: token and error are both null"))
                    }
                }
            }

            // 실제 카카오 로그인 로직 실행
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        // 사용자가 직접 취소한 경우는 CancellationException으로 처리하여
                        // 코루틴의 표준 취소 흐름을 따르게 합니다.
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.cancel(CancellationException("User cancelled the login.", error))
                        } else {
                            // 그 외 에러는 카카오 계정 로그인으로 fallback
                            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                        }
                    } else {
                        callback(token, null)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }

    private fun mapToKakaoResponse(token: OAuthToken): KakaoResponse =
        KakaoResponse(
            accessToken = token.accessToken,
            accessTokenExpiresAt = token.accessTokenExpiresAt,
            refreshToken = token.refreshToken,
            refreshTokenExpiresAt = token.refreshTokenExpiresAt,
            idToken = token.idToken,
            scopes = token.scopes
        )
}