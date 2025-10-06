package com.kimbh.auth_naver.client

import android.content.Context
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.data.model.TokenInfoDto
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class NaverLoginClient @Inject constructor(
    @param:ApplicationContext val context: Context
) : LoginClient {
    override val authType: AuthType
        get() = AuthType.NAVER

    override suspend fun login(): Result<TokenInfoDto> = runCatching {
        suspendCancellableCoroutine { continuation ->
            val oauthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                    continuation.resume(
                        TokenInfoDto(
                            accessToken = NaverIdLoginSDK.getAccessToken() ?: "",
                            newToken = NaverIdLoginSDK.getRefreshToken() ?: ""
                        )
                    )
//                    binding.tvAccessToken.text = NaverIdLoginSDK.getAccessToken()
//                    binding.tvRefreshToken.text = NaverIdLoginSDK.getRefreshToken()
//                    binding.tvExpires.text = NaverIdLoginSDK.getExpiresAt().toString()
//                    binding.tvType.text = NaverIdLoginSDK.getTokenType()
//                    binding.tvState.text = NaverIdLoginSDK.getState().toString()
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    continuation.resumeWith(
                        Result.failure(IllegalStateException("errorCode : $errorCode, errorDescription : $errorDescription"))
                    )
                }

                override fun onError(errorCode: Int, message: String) {
                    continuation.resumeWith(
                        Result.failure(IllegalStateException("errorCode : $errorCode, errorDescription : $message"))
                    )
                }
            }
            NaverIdLoginSDK.authenticate(
                context = context.applicationContext,
                callback = oauthLoginCallback
            )
        }
    }
}