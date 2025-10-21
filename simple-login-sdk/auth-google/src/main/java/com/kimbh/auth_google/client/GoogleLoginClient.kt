package com.kimbh.auth_google.client

import android.content.Context
import com.kimbh.auth_google.GoogleAuth
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.data.model.TokenInfoDto
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleLoginClient @Inject constructor(
    @param:ApplicationContext val context: Context
) : LoginClient {
    override val authType: AuthType
        get() = AuthType.GOOGLE

    override suspend fun login(): Result<TokenInfoDto> = runCatching {
        val token = GoogleAuth.login(context)

        TokenInfoDto(
            authType = AuthType.GOOGLE,
            accessToken = token, // Google의 idToken을 우리 시스템의 accessToken으로 간주
            newToken = token // 또는 필요에 따라 idToken
        )
    }
}