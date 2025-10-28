package com.kimbh.auth_google.model

import com.kimbh.core.model.UserInfoGender
import com.kimbh.data.model.UserInfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("localId")
    val id: String,
    @SerialName("email")
    val email: String,
    @SerialName("displayName")
    val nickName: String,
    @SerialName("photoUrl")
    val profileImage: String,
    @SerialName("dateOfBirth")
    val birthday: String
)

fun UserInfoResponse.toDto(): UserInfoDto =
    UserInfoDto(
        id = this.id,
        email = this.email,
        nickName = this.nickName,
        profileImage = this.profileImage,
        gender = UserInfoGender.OTHER,
        birthday = birthday
    )
