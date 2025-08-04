package com.kimbh.sdk_auth

import android.content.Context
import com.kakao.sdk.common.KakaoSdk

object AuthManager {

    fun kakaoInit(context: Context, appKey: String) {
        KakaoSdk.init(context = context, appKey = appKey)
    }
}