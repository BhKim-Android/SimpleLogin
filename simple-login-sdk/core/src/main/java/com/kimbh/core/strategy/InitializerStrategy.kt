package com.kimbh.core.strategy

import android.content.Context
import com.kimbh.core.utils.AuthType

interface InitializerStrategy {
    val authType: AuthType
    fun initialize(context: Context, appKey: String)
}