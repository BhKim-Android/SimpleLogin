package com.kimbh.auth_google.datasource

import com.kimbh.auth_google.model.UserInfoResponse


interface GoogleUserInfoDataSource {
    suspend fun getUserInfo(token: String): Result<UserInfoResponse>
}