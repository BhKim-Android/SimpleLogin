package com.kimbh.auth_google

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.suspendCancellableCoroutine

object GoogleAuth {
    private lateinit var googleIdOption: GetGoogleIdOption


    /**
     * 구글 로그인 요청 인스턴스
     * https://developer.android.com/identity/sign-in/credential-manager-siwg?utm_source=chatgpt.com&hl=ko#instantiate-google
     * @param serverClientId : OAuth 2.0 클라이언트 ID
     * @param nonce : https://developer.android.com/identity/sign-in/credential-manager-siwg?utm_source=chatgpt.com&hl=ko#set-nonce
     **/
    fun init(serverClientId: String, nonce: String? = null) {
        googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(serverClientId)
            .setAutoSelectEnabled(true)
            // nonce string to use when generating a Google ID token
            .setNonce(nonce)
            .build()
    }

    /**
     * 구글 계정 로그인 흐름
     * https://developer.android.com/identity/sign-in/credential-manager-siwg?utm_source=chatgpt.com&hl=ko#create-sign
     **/
    suspend fun login(context: Context): String {
        if (!::googleIdOption.isInitialized) {
            throw IllegalStateException("GoogleAuth is not initialized")
        }

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            // 2. getCredential은 이미 suspend 함수이므로 결과를 바로 받습니다.
            val result = CredentialManager.create(context).getCredential(
                context = context.applicationContext,
                request = request
            )
            // 3. handleSignIn이 idToken을 반환하도록 합니다.
            return handleSignIn(result)
        } catch (e: GetCredentialException) {
            // Handle failure
            throw e
        }
    }

    private fun handleSignIn(result: GetCredentialResponse): String {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    return try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        // 5. idToken을 추출하여 반환합니다.
                        googleIdTokenCredential.idToken
                    } catch (e: GoogleIdTokenParsingException) {
                        throw e
                    }
                } else {
                    throw Throwable("Unexpected type of custom credential")
                }
            }
            // Passkey나 Password는 이 흐름에서 지원하지 않으므로 예외 처리
            is PublicKeyCredential -> {
                throw Throwable("Unsupported credential type: Passkey")
            }
            is PasswordCredential -> {
                throw Throwable("Unsupported credential type: Password")
            }
            else -> {
                throw Throwable("Unexpected type of credential")
            }
        }
    }
}