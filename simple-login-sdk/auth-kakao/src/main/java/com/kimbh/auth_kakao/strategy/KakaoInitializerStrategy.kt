package com.kimbh.auth_kakao.strategy

import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.kimbh.core.utils.AuthType
import com.kimbh.core.strategy.InitializerStrategy

class KakaoInitializerStrategy : InitializerStrategy {
    override val authType
        get() = AuthType.KAKAO

    override fun initialize(context: Context, appKey: String) {
        KakaoSdk.init(context = context, appKey = appKey)
    }
}