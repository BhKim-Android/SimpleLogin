package com.kimbh.data.client

import com.kimbh.core.utils.AuthType
import com.kimbh.data.model.UserInfoDto

interface UserInfoClient {
    val authType: AuthType
    suspend fun getUserInfo(token: String): Result<UserInfoDto>
}