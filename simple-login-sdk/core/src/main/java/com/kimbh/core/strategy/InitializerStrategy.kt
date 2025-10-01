package com.kimbh.core.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType
import com.kimbh.core.utils.PlatformConfig

interface InitializerStrategy {
    val authType: AuthType
    fun initialize(context: Context, platformConfig: PlatformConfig)
}