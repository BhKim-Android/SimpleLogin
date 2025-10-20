package com.kimbh.simplelogin.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kimbh.simplelogin.model.UiState
import com.kimbh.simplelogin.ui.compose.login.LoginDestination
import com.kimbh.simplelogin.ui.compose.login.LoginScreen
import com.kimbh.simplelogin.ui.compose.token.TokenDestination
import com.kimbh.simplelogin.ui.compose.token.TokenScreen
import com.kimbh.simplelogin.ui.compose.userinfo.UserInfoDestination
import com.kimbh.simplelogin.ui.compose.userinfo.UserInfoScreen
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel


@Composable
fun SimpleLoginNavigation(
    innerPadding: PaddingValues,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel(),
    facebookLogin: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<LoginDestination> {
            val tokenState = viewModel.tokenState.collectAsState()

            LaunchedEffect(tokenState.value) {
                if (tokenState.value != UiState.Loading) {
                    navController.navigate(TokenDestination) {
                        popUpTo(LoginDestination) { inclusive = false }
                    }
                }
            }

            LoginScreen(viewModel, facebookLogin)
        }
        composable<TokenDestination> {
            TokenScreen(
                viewModel,
                onClick = { navController.navigate(UserInfoDestination) }
            )
        }
        composable<UserInfoDestination> {
            UserInfoScreen(viewModel)
        }
    }
}