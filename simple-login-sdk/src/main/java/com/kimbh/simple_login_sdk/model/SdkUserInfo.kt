package com.kimbh.simple_login_sdk.model

import com.kimbh.core.model.UserInfoGender
import com.kimbh.core.utils.AuthType

data class SdkUserInfo(
    val authType: AuthType,
    val id: String,
    val email: String?,
    val nickName: String?,
    val profileImage: String?,
    val gender: UserInfoGender?,
    val birthday: String?
)
