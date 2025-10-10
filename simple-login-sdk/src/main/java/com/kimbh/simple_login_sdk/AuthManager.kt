package com.kimbh.simple_login_sdk

import android.app.Activity
import android.content.Context
import android.util.Log
import com.kimbh.core.utils.AuthConfig
import com.kimbh.core.utils.AuthType
import com.kimbh.domain.model.TokenInfo
import com.kimbh.simple_login_sdk.SdkContextHolder
import com.kimbh.simple_login_sdk.di.AuthManagerEntryPoint
import com.kimbh.simple_login_sdk.facade.SdkInitializer
import com.kimbh.simple_login_sdk.model.SdkTokenInfo
import com.kimbh.simple_login_sdk.model.SdkUserInfo
import dagger.hilt.android.EntryPointAccessors

object AuthManager {

    // SdkInitializer 인스턴스를 저장할 변수 (지연 초기화 및 캐싱 목적)
    @Volatile
    private var sdkInitializer: SdkInitializer? = null
    private val initializedPlatforms = mutableSetOf<AuthType>()
//    private lateinit var appContext: Context

    /**
     * @param context : 앱의 초기화를 할때 필요한 context (Application Context)
     * @param authConfig : 다양한 플랫폼의 App Key를 포함하는 설정 객체
     * @return : 초기화 성공한 플랫폼(AuthType)
     **/
    fun initialize(
        context: Context,
        authConfig: AuthConfig
    ) {
        SdkContextHolder.initialize(context.applicationContext)
        initializedPlatforms.addAll(
            getSdkInitializer(context).initialize(context = context, authConfig = authConfig)
        )
        initializedPlatforms.forEach {
            Log.d("kakao", it.name)
        }
    }

    private fun getSdkInitializer(context: Context): SdkInitializer {
        // 이미 인스턴스가 있을때 기존의 sdkInitializer 리턴
        sdkInitializer?.let { return it }

        // 동기화 블록으로 멀티 스레드 환경에서 안전하게 인스턴스를 한 번만 생성
        synchronized(this) {
            // 다시 한번 null 체크
            if (sdkInitializer == null) {
                // EntryPointAccessors를 사용해 EntryPoint의 구현체를 얻어옵니다.
                val entryPoint = EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    AuthManagerEntryPoint::class.java
                )
                // EntryPoint를 통해 SdkInitializer를 주입받아 변수에 할당합니다.
                sdkInitializer = entryPoint.getSdkInitializer()
            }
            return sdkInitializer!!
        }
    }

    suspend fun login(authType: AuthType): Result<SdkTokenInfo> {
        return getEntryPoint(authType).fold(
            onSuccess = { entryPoint ->
                try {
                    entryPoint.getLoginHandler().login(authType).map {
                        SdkTokenInfo(accessToken = it.accessToken, newToken = it.newToken)
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            },
            onFailure = { Result.failure(it) }
        )
    }

    suspend fun getUserInfo(authType: AuthType): Result<SdkUserInfo> {
        return getEntryPoint(authType = authType).fold(
            onSuccess = { entryPoint ->
                try {
                    entryPoint.getUserInfoHandler().getUserInfo(authType = authType).map {
                        SdkUserInfo(
                            id = it.id,
                            email = it.email,
                            nickName = it.nickName,
                            profileImage = it.profileImage,
                            gender = it.gender,
                            birthday = it.birthday
                        )
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            },
            onFailure = { Result.failure(it) }
        )
    }

    private fun getEntryPoint(authType: AuthType): Result<AuthManagerEntryPoint> {
        return try {
            Result.success(
                EntryPointAccessors.fromApplication(
                    context = SdkContextHolder.getApplicationContext(),
                    entryPoint = AuthManagerEntryPoint::class.java
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}