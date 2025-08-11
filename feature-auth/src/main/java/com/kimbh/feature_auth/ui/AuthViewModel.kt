package com.kimbh.feature_auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimbh.core.common.AuthResult
import com.kimbh.feature_auth.model.AuthUiModel
import com.kimbh.simplelogin.domain.AuthUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUsecase: AuthUsecase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiModel())
    val uiState: StateFlow<AuthUiModel> = _uiState

    fun login() = viewModelScope.launch {
        when (val result = authUsecase()) {
            is AuthResult.Success -> {
                _uiState.value = AuthUiModel(
                    accessToken = result.data.accessToken,
                    accessTokenExpiresAt = result.data.accessTokenExpiresAt,
                    refreshToken = result.data.refreshToken,
                    refreshTokenExpiresAt = result.data.refreshTokenExpiresAt,
                    idToken = result.data.idToken,
                    scopes = result.data.scopes
                )
            }

            is AuthResult.Error -> {

            }
        }
    }
}