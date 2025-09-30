package com.kimbh.simple_login_sdk.facade

import android.content.Context
import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.core.strategy.InitializerStrategy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SdkInitializer @Inject constructor(
    private val initializerStrategy: Set<@JvmSuppressWildcards InitializerStrategy>
) {
    fun initialize(context: Context, authConfig: AuthConfig): Set<AuthType> {
        val initAuthSet = mutableSetOf<AuthType>()
        authConfig.appKeys.forEach { (authType, appkey) ->
            try {
                initializerStrategy.find { initializerStrategy ->
                    initializerStrategy.authType == authType
                }?.let { initializer ->
                    initializer.initialize(context = context, appKey = appkey)
                    initAuthSet.add(initializer.authType)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return initAuthSet
    }
}