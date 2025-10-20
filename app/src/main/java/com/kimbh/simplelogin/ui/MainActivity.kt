package com.kimbh.simplelogin.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.kimbh.simple_login_sdk.AuthFacebookManager
import com.kimbh.simplelogin.model.UiState
import com.kimbh.simplelogin.ui.compose.SimpleLoginNavigation
import com.kimbh.simplelogin.ui.theme.SimpleLoginTheme
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SimpleLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleLoginNavigation(
                        innerPadding,
                        facebookLogin = {
                            lifecycleScope.launch {
                                viewModel.bindFacebookTokenState(UiState.Loading)
                                val result = AuthFacebookManager.login(this@MainActivity)
                                result.onSuccess {
                                    viewModel.bindFacebookTokenState(UiState.Success(it))
                                }.onFailure {
                                    viewModel.bindFacebookTokenState(UiState.Error(it.message))
                                }
                            }
                        })
                }
            }
        }
    }
}