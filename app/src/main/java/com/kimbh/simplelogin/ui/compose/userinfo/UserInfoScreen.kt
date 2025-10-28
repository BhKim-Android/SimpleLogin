package com.kimbh.simplelogin.ui.compose.userinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kimbh.simplelogin.model.UiState
import com.kimbh.simplelogin.ui.compose.utils.ErrorContent
import com.kimbh.simplelogin.ui.compose.utils.LoadingContent
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
object UserInfoDestination

@Composable
fun UserInfoScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val userinfoState = viewModel.userInfoState.collectAsState()

    when (val state = userinfoState.value) {
        is UiState.Loading -> LoadingContent(Modifier)
        is UiState.Success -> UserInfoContent(Modifier, sdkUserInfo = state.data)
        is UiState.Error -> ErrorContent(Modifier, state.error)
    }
}