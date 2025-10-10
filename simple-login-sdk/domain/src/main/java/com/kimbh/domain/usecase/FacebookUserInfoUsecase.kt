package com.kimbh.domain.usecase

import com.kimbh.domain.model.UserInfo
import com.kimbh.domain.repository.UserInfoFacebookRepository
import javax.inject.Inject

class FacebookUserInfoUsecase @Inject constructor(
    private val userInfoFacebookRepository: UserInfoFacebookRepository
) {
    suspend operator fun invoke(): Result<UserInfo> {
        return userInfoFacebookRepository.getUserInfo()
    }
}