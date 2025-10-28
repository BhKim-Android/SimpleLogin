package com.kimbh.auth_kakao.client

import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.Gender
import com.kakao.sdk.user.model.User
import com.kimbh.core.model.UserInfoGender
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.UserInfoClient
import com.kimbh.data.model.UserInfoDto
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class KakaoUserInfoClient @Inject constructor() : UserInfoClient {
    override val authType: AuthType
        get() = AuthType.KAKAO

    override suspend fun getUserInfo(token: String): Result<UserInfoDto> = runCatching {
        val user = getUser()
        val id = user.id ?: throw IllegalStateException("Kakao user id is null")
        UserInfoDto(
            id = id.toString(),
            email = user.kakaoAccount?.email,
            nickName = user.kakaoAccount?.profile?.nickname,
            profileImage = user.kakaoAccount?.profile?.profileImageUrl,
            gender = when (user.kakaoAccount?.gender) {
                Gender.MALE -> {
                    UserInfoGender.MALE
                }

                Gender.FEMALE -> {
                    UserInfoGender.FEMALE
                }

                else -> {
                    UserInfoGender.OTHER
                }
            },
            birthday = user.kakaoAccount?.birthday
        )
    }

    private suspend fun getUser(): User {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.me { user, error ->
                when {
                    user != null -> continuation.resume(user)
                    error != null -> continuation.resumeWith(Result.failure(error))
                    else -> continuation.resumeWith(Result.failure(Throwable("Kakao 계정 정보를 받을 수 없습니다.")))
                }
            }
        }
    }
}