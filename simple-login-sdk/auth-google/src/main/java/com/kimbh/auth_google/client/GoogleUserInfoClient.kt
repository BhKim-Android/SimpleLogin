package com.kimbh.auth_google.client

import com.kimbh.auth_google.datasource.GoogleUserInfoDataSource
import com.kimbh.auth_google.model.toDto
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.UserInfoClient
import com.kimbh.data.model.UserInfoDto
import javax.inject.Inject

class GoogleUserInfoClient @Inject constructor(
    private val googleUserInfoDataSource: GoogleUserInfoDataSource
) : UserInfoClient {
    override val authType: AuthType
        get() = AuthType.GOOGLE

    override suspend fun getUserInfo(token: String): Result<UserInfoDto> {
        return googleUserInfoDataSource.getUserInfo(token).map { it.toDto() }
    }
}