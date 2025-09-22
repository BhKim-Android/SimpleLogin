package com.kimbh.simple_login_sdk.facade

import android.content.Context
import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.strategy.InitializerStrategy
import javax.inject.Inject

internal class SdkInitializer @Inject constructor(
    private val initializerStrategy: Set<@JvmSuppressWildcards InitializerStrategy>
) {

    fun initialize(context: Context, authConfig: AuthConfig): Set<AuthType> {
        val initAuthSet = mutableSetOf<AuthType>()
        authConfig.appKeys.forEach { (authType, appkey) ->
            try {
                initializerStrategy.find { initializerStrategy ->
                    initializerStrategy.authType == authType
                }?.let {
                    it.initialize(context = context, appKey = appkey)
                    initAuthSet.add(it.authType)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return initAuthSet
    }
}