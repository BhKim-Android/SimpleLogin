package com.kimbh.simplelogin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.AuthFacebookManager
import com.kimbh.simple_login_sdk.AuthManager
import com.kimbh.simple_login_sdk.model.SdkTokenInfo
import com.kimbh.simple_login_sdk.model.SdkUserInfo
import com.kimbh.simplelogin.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _tokenState = MutableStateFlow<UiState<SdkTokenInfo>>(UiState.Loading)
    val tokenState: StateFlow<UiState<SdkTokenInfo>> = _tokenState
    private val _userInfoState = MutableStateFlow<UiState<SdkUserInfo>>(UiState.Loading)
    val userInfoState: StateFlow<UiState<SdkUserInfo>> = _userInfoState
    private var lastAuthType: AuthType? = null

    fun bindFacebookTokenState(uiTokenState: UiState<SdkTokenInfo>) {
        lastAuthType = AuthType.FACEBOOK
        _tokenState.value = uiTokenState
    }

    fun onLoginClicked(authType: AuthType) = viewModelScope.launch {
        _tokenState.value = UiState.Loading
        lastAuthType = authType
        AuthManager.login(authType = authType).onSuccess {
            _tokenState.value = UiState.Success(it)
        }.onFailure {
            _tokenState.value = UiState.Error(it.message)
        }
    }

    fun getUserInfo() = viewModelScope.launch {
        _userInfoState.value = UiState.Loading
        if (lastAuthType == AuthType.FACEBOOK) {
            AuthFacebookManager.getUserInfo()
                .onSuccess { sdkUserInfo ->
                    _userInfoState.value = UiState.Success(sdkUserInfo)
                }
                .onFailure {
                    _userInfoState.value = UiState.Error(it.message)
                }
        } else {
            lastAuthType?.let { authType ->
                AuthManager.getUserInfo(authType = authType)
                    .onSuccess { sdkUserInfo ->
                        _userInfoState.value = UiState.Success(sdkUserInfo)
                    }
                    .onFailure {
                        _userInfoState.value = UiState.Error(it.message)
                    }
            }
        }
    }
}