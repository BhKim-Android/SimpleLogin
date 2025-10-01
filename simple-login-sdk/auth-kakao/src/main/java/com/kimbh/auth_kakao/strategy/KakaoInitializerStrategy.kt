package com.kimbh.auth_kakao.strategy

import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.kimbh.auth_kakao.config.KakaoAuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.core.strategy.InitializerStrategy
import com.kimbh.core.utils.PlatformConfig

class KakaoInitializerStrategy : InitializerStrategy {
    override val authType
        get() = AuthType.KAKAO

    override fun initialize(
        context: Context,
        platformConfig: PlatformConfig
    ) {
        if (platformConfig is KakaoAuthConfig) {
            KakaoSdk.init(context = context, appKey = platformConfig.appkey)
        } else {
            throw Throwable("KakaoInitializer: Invalid config type provided.")
        }
    }
}