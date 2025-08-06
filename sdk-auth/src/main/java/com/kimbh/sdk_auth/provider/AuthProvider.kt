package com.kimbh.sdk_auth.provider

import android.content.Context
import com.kimbh.sdk_auth.model.KakaoResponse
import com.kimbh.simplelogin.domain.common.AuthResult

interface AuthProvider {
    fun login(
        context: Context,
        callback: (AuthResult<KakaoResponse>) -> Unit
    )
}