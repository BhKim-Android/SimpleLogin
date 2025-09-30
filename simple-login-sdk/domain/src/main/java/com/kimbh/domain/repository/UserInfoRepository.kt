package com.kimbh.domain.repository

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.UserInfo

interface UserInfoRepository {
    suspend fun getUserInfo(authType: AuthType): Result<UserInfo>
}