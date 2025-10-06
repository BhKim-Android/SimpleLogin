package com.kimbh.auth_facebook.client

import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.data.model.TokenInfoDto
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@ActivityScoped
class FacebookLoginClient @Inject constructor(
    private val activity: ComponentActivity,
    private val callbackManager: CallbackManager,
    private val loginManager: LoginManager
) : LoginClient {
    override val authType: AuthType
        get() = AuthType.FACEBOOK

    override suspend fun login(): Result<TokenInfoDto> = runCatching {
        callbackManager.onActivityResult()
        suspendCancellableCoroutine { continuation ->
            loginManager.registerCallback(
                callbackManager = callbackManager,
                callback = object : FacebookCallback<LoginResult> {
                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }

                    override fun onError(error: FacebookException) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(result: LoginResult) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }
}