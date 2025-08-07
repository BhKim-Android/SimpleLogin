package com.kimbh.sdk_auth.provider

import android.content.Context
import com.kimbh.core.common.AuthResult
import com.kimbh.sdk_auth.model.KakaoResponse

interface AuthProvider {
    fun login(
        callback: (AuthResult<KakaoResponse>) -> Unit
    )
}