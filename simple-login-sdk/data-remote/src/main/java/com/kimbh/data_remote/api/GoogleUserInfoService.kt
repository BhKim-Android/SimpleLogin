package com.kimbh.data_remote.api

import com.kimbh.auth_google.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface GoogleUserInfoService {

    @GET("oauth2/v3/userinfo")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String
    ): Result<UserInfoResponse>
}