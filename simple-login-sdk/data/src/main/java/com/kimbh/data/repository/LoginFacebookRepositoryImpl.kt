package com.kimbh.data.repository

import com.kimbh.data.client.LoginClient
import com.kimbh.domain.model.TokenInfo
import com.kimbh.domain.repository.LoginFacebookRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LoginFacebookRepositoryImpl @Inject constructor(
    private val loginClient: LoginClient
) : LoginFacebookRepository {
    override suspend fun login(): Result<TokenInfo> {
        return loginClient.login().map { tokenInfoDto ->
            TokenInfo(accessToken = tokenInfoDto.accessToken, newToken = tokenInfoDto.newToken)
        }
    }
}