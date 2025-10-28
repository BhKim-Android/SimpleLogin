package com.kimbh.simple_login_sdk

import android.app.Activity
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.di.FacebookLoginEntryPoint
import com.kimbh.simple_login_sdk.model.SdkTokenInfo
import dagger.hilt.android.EntryPointAccessors

object AuthFacebookManager {

    suspend fun login(activity: Activity): Result<SdkTokenInfo> {
        return try {
            val entryPoint = EntryPointAccessors.fromActivity(
                activity = activity,
                entryPoint = FacebookLoginEntryPoint::class.java
            )
            entryPoint.getLoginUsecase().invoke().map { tokenInfo ->
                SdkTokenInfo(
                    authType = AuthType.FACEBOOK,
                    accessToken = tokenInfo.accessToken,
                    newToken = tokenInfo.newToken
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}