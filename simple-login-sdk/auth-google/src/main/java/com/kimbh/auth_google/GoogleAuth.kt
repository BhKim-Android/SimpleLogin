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
    suspend fun login(context: Context) {
        if (!::googleIdOption.isInitialized) {
            throw IllegalStateException("GoogleAuth is not initialized")
        }

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = CredentialManager.create(context).getCredential(
                context = context.applicationContext,
                request = request
            )
            handleSignIn(result)
        } catch (e: GetCredentialException) {
            // Handle failure
            throw e
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        val credential = result.credential
        val responseJson: String

        when (credential) {

            // Passkey credential
            is PublicKeyCredential -> {
                // Share responseJson such as a GetCredentialResponse to your server to validate and
                // authenticate
                responseJson = credential.authenticationResponseJson
            }

            // Password credential
            is PasswordCredential -> {
                // Send ID and password to your server to validate and authenticate.
                val username = credential.id
                val password = credential.password
            }

            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract the ID to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        // You can use the members of googleIdTokenCredential directly for UX
                        // purposes, but don't use them to store or control access to user
                        // data. For that you first need to validate the token:
                        // pass googleIdTokenCredential.getIdToken() to the backend server.
                        // see [validation instructions](https://developers.google.com/identity/gsi/web/guides/verify-google-id-token)
                    } catch (e: GoogleIdTokenParsingException) {
                        throw e
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
//                    Log.e(TAG, "Unexpected type of credential")
                    throw Throwable("Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
//                Log.e(TAG, "Unexpected type of credential")
                throw Throwable("Unexpected type of credential")
            }
        }
    }
}