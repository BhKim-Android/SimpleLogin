package com.kimbh.data.repository

import com.kimbh.data.client.UserInfoClient
import com.kimbh.domain.model.UserInfo
import com.kimbh.domain.repository.UserInfoFacebookRepository
import javax.inject.Inject

class UserInfoFacebookRepositoryImpl @Inject constructor(
    private val userInfoClient: UserInfoClient
) : UserInfoFacebookRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return userInfoClient.getUserInfo().map { userInfoDto ->
            UserInfo(
                id = userInfoDto.id,
                email = userInfoDto.email,
                nickName = userInfoDto.nickName,
                profileImage = userInfoDto.profileImage,
                gender = userInfoDto.gender,
                birthday = userInfoDto.birthday
            )
        }
    }
}