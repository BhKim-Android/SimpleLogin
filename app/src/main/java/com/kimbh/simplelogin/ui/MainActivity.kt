package com.kimbh.simplelogin.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthFacebookManager
import com.kimbh.simplelogin.ui.theme.SimpleLoginTheme
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val result = AuthFacebookManager.login(this@MainActivity)
            result.onSuccess {
                Log.d("Facebook_bh", "$it")
            }.onFailure {
                Log.e("Facebook_bh", "$it")
            }
        }
        enableEdgeToEdge()
        setContent {
            SimpleLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(
    innerPadding: PaddingValues,
    viewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kakao Login",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable {
                    viewModel.onLoginClicked(AuthType.KAKAO)
                }
        )
    }
}