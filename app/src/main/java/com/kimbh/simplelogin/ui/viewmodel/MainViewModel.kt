package com.kimbh.simplelogin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun onLoginClicked(authType: AuthType) = viewModelScope.launch {
        AuthManager.login(authType = authType).onSuccess { (accessToken, newToken) ->
            Log.d("Toss", "accessToken : $accessToken\nnewToken : $newToken")
        }.onFailure {
            Log.e("Toss", it.message ?: "Error MainViewModel")
        }
    }
}