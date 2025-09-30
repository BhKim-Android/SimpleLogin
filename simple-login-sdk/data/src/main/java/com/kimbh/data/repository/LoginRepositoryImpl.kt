package com.kimbh.data.repository

import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.domain.model.TokenInfo
import com.kimbh.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginClients: Set<@JvmSuppressWildcards LoginClient>
) : LoginRepository {
    override suspend fun login(authType: AuthType): Result<TokenInfo> {
        return loginClients.find { it.authType ==  authType}?.login()?.map {
            TokenInfo(accessToken = it.accessToken, newToken = it.newToken)
        } ?: Result.failure(Throwable("Unsupported AuthType: $authType"))
    }
}