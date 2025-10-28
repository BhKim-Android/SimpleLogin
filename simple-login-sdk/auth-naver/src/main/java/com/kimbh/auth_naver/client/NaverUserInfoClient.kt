package com.kimbh.auth_naver.client

import com.kimbh.core.model.UserInfoGender
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.UserInfoClient
import com.kimbh.data.model.UserInfoDto
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class NaverUserInfoClient @Inject constructor() : UserInfoClient {
    override val authType: AuthType
        get() = AuthType.NAVER

    override suspend fun getUserInfo(token: String): Result<UserInfoDto> = runCatching {
        suspendCancellableCoroutine { continuation ->
            val nidProfileCallback = object : NidProfileCallback<NidProfileResponse> {
                override fun onSuccess(result: NidProfileResponse) {
                    val id =
                        result.profile?.id ?: throw IllegalStateException("Naver user id is null")
                    continuation.resume(
                        UserInfoDto(
                            id = id,
                            email = result.profile?.email,
                            nickName = result.profile?.nickname,
                            profileImage = result.profile?.profileImage,
                            gender = when (result.profile?.gender) {
                                "F" -> UserInfoGender.FEMALE
                                "M" -> UserInfoGender.MALE
                                else -> UserInfoGender.OTHER
                            },
                            birthday = result.profile?.birthday
                        )
                    )
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
            NidOAuthLogin().callProfileApi(callback = nidProfileCallback)
        }
    }
}