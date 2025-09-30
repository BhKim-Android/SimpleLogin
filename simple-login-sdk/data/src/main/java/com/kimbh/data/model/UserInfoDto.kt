package com.kimbh.data.model

import com.kimbh.core.model.UserInfoGender

data class UserInfoDto(
    val id: Long,
    val email: String?,
    val nickName: String?,
    val profileImage: String?,
    val gender: UserInfoGender?,
    val birthday: String?
)
