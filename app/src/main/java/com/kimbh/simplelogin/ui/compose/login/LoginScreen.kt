package com.kimbh.simplelogin.ui.compose.login

import androidx.compose.runtime.Composable
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination

@Composable
fun LoginScreen(
    viewModel: MainViewModel,
    facebookLogin: () -> Unit
) {
    LoginContent(
        onLoginClicked = { authType -> viewModel.onLoginClicked(authType) },
        facebookLogin = facebookLogin
    )
}