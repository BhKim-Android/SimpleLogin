package com.kimbh.simple_login_sdk.facade

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.UserInfo
import com.kimbh.domain.usecase.UserInfoUseCase
import javax.inject.Inject

class UserInfoHandler @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase
) {
    suspend fun getUserInfo(authType: AuthType, token: String): Result<UserInfo> {
        return userInfoUseCase(authType = authType, token = token)
    }
}