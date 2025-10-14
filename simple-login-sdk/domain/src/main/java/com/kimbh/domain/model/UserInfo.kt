package com.kimbh.domain.model

import com.kimbh.core.model.UserInfoGender

data class UserInfo(
    val id: String,
    val email: String?,
    val nickName: String?,
    val profileImage: String?,
    val gender: UserInfoGender?,
    val birthday: String?
)
