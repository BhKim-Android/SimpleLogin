package com.kimbh.domain.repository

import com.kimbh.domain.model.UserInfo

interface UserInfoFacebookRepository {
    suspend fun getUserInfo(): Result<UserInfo>
}