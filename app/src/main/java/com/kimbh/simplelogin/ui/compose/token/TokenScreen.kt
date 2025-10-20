package com.kimbh.simplelogin.ui.compose.token

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kimbh.simplelogin.model.UiState
import com.kimbh.simplelogin.ui.compose.utils.ErrorContent
import com.kimbh.simplelogin.ui.compose.utils.LoadingContent
import com.kimbh.simplelogin.ui.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
object TokenDestination

@Composable
fun TokenScreen(
    viewModel: MainViewModel,
    onClick: () -> Unit
) {
    val tokenState = viewModel.tokenState.collectAsState()

    when (val state = tokenState.value) {
        is UiState.Loading -> LoadingContent(Modifier)
        is UiState.Success -> TokenContent(state.data, onClick)
        is UiState.Error -> ErrorContent(Modifier, state.error)
    }
}