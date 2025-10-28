package com.kimbh.data.repository

import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.UserInfoClient
import com.kimbh.domain.model.UserInfo
import com.kimbh.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoClient: Set<@JvmSuppressWildcards UserInfoClient>
) : UserInfoRepository {
    override suspend fun getUserInfo(authType: AuthType, token: String): Result<UserInfo> {
        return userInfoClient.find { it.authType == authType }?.getUserInfo(token = token)?.map {
            UserInfo(
                id = it.id,
                email = it.email,
                nickName = it.nickName,
                profileImage = it.profileImage,
                gender = it.gender,
                birthday = it.birthday
            )
        } ?: Result.failure(Throwable("Unsupported AuthType: $authType"))
    }
}