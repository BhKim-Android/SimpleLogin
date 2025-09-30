package com.kimbh.simple_login_sdk.model

import com.kimbh.core.model.UserInfoGender

data class SdkUserInfo(
    val id: Long,
    val email: String?,
    val nickName: String?,
    val profileImage: String?,
    val gender: UserInfoGender?,
    val birthday: String?
)
