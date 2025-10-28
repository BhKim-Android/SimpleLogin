package com.kimbh.data_remote.datasource

import com.kimbh.auth_google.datasource.GoogleUserInfoDataSource
import com.kimbh.auth_google.model.UserInfoResponse
import com.kimbh.data_remote.api.GoogleUserInfoService
import javax.inject.Inject

class GoogleUserInfoDataSourceImpl @Inject constructor(
    private val googleUserInfoService: GoogleUserInfoService
): GoogleUserInfoDataSource {

    override suspend fun getUserInfo(token: String): Result<UserInfoResponse> {
        return googleUserInfoService.getUserInfo(authorization = token)
    }
}