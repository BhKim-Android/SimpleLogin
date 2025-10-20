package com.kimbh.simplelogin.ui.compose.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ErrorContent(
    modifier: Modifier,
    message: String?
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message ?: "Exception",
            color = Color(0xFFFF5A54),
            fontSize = 20.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorContentPreview() {
    ErrorContent(Modifier, "Exception")
}