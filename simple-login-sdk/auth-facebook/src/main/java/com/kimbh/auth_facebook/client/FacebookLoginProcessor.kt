package com.kimbh.auth_facebook.client

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kimbh.core.utils.AuthType
import com.kimbh.data.client.LoginClient
import com.kimbh.data.model.TokenInfoDto
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume

@ActivityScoped
class FacebookLoginProcessor @Inject constructor(
    private val activity: ComponentActivity
) : DefaultLifecycleObserver, LoginClient {

    private val callbackManager = CallbackManager.Factory.create()
    private val loginManager = LoginManager.getInstance()
    private var loginContinuation: CancellableContinuation<Result<TokenInfoDto>>? = null

    override val authType: AuthType
        get() = AuthType.FACEBOOK

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        activity.activityResultRegistry.register(
            "facebook_login_key",
            owner,
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            callbackManager.onActivityResult(
                requestCode = FacebookSdk.getCallbackRequestCodeOffset(),
                resultCode = result.resultCode,
                data = result.data
            )
        }
    }

    override suspend fun login(): Result<TokenInfoDto> =
        suspendCancellableCoroutine { continuation ->
            loginContinuation = continuation
            loginManager.registerCallback(
                callbackManager = callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onCancel() {
                        continuation.resume(Result.failure(CancellationException("Facebook login canceled.")))
                        cleanup()
                    }

                    override fun onError(error: FacebookException) {
                        continuation.resume(Result.failure(error))
                        cleanup()
                    }

                    override fun onSuccess(result: LoginResult) {
                        continuation.resume(
                            Result.success(
                                TokenInfoDto(
                                    authType = authType,
                                    accessToken = result.accessToken.token,
                                    newToken = ""
                                )
                            )
                        )
                        cleanup()
                    }
                })
            loginManager.logInWithReadPermissions(activity, listOf("email", "public_profile"))

            continuation.invokeOnCancellation { cleanup() }
        }

    private fun cleanup() {
//        activity.lifecycle.removeObserver(this)
        loginContinuation = null
    }
}