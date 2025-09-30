package com.kimbh.domain.usecase

import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.UserInfo
import com.kimbh.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(authType: AuthType): Result<UserInfo> {
        return userInfoRepository.getUserInfo(authType = authType)
    }
}