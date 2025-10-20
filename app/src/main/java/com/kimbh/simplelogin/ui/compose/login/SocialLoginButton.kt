package com.kimbh.simplelogin.ui.compose.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kimbh.simplelogin.R

@Composable
fun SocialLoginButton(
    painter: Painter,
    contentDescription: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    val currentOnClick by rememberUpdatedState(onClick)

    Surface(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .clickable { currentOnClick() },
        color = backgroundColor,
        shape = CircleShape,
        shadowElevation = 4.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Preview
@Composable
fun SocialLoginButtonPreview() {
    SocialLoginButton(
        painter = painterResource(R.drawable.facebook_logo),
        contentDescription = "Facebook login",
        backgroundColor = Color(0xFF1877F2),
        onClick = { }
    )
}