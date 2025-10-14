package com.kimbh.auth_facebook.client

import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.UserInfoClient
import com.kimbh.data.model.UserInfoDto
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.resume

class FacebookUserInfoClient @Inject constructor() : UserInfoClient {
    override val authType: AuthType
        get() = AuthType.FACEBOOK

    override suspend fun getUserInfo(): Result<UserInfoDto> =
        suspendCancellableCoroutine { continuation ->
            AccessToken.getCurrentAccessToken()
            GraphRequest.newMeRequest(
                accessToken = AccessToken.getCurrentAccessToken(),
                callback = object : GraphRequest.GraphJSONObjectCallback {
                    override fun onCompleted(
                        obj: JSONObject?,
                        response: GraphResponse?
                    ) {
                        response?.error?.let { error ->
                            error.exception?.let { exception ->
                                continuation.resume(Result.failure(exception))
                            } ?: let {
                                continuation.resume(Result.failure(FacebookException("페이스북 유저정보를 가져오는데 실패 했습니다.")))
                            }
                            return
                        }

                        if (obj == null) {
                            continuation.resume(Result.failure(NullPointerException("Facebook returned null user info")))
                            return
                        }

                        continuation.resume(
                            Result.success(
                                UserInfoDto(
                                    id = obj.optString("id", ""),
                                    email = obj.optString("email", null),
                                    nickName = obj.optString("name", null),
                                    profileImage = obj.optJSONObject("picture")
                                        ?.optJSONObject("data")
                                        ?.optString("url", null),
                                    gender = null,
                                    birthday = obj.optString("birthday", null)
                                )
                            )
                        )
                    }
                }).executeAsync()
        }
}