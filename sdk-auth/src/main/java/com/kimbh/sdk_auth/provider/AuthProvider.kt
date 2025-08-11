package com.kimbh.sdk_auth.provider

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kimbh.core.common.AuthResult
import com.kimbh.sdk_auth.model.KakaoResponse

interface AuthProvider {
    suspend fun login(): KakaoResponse
}