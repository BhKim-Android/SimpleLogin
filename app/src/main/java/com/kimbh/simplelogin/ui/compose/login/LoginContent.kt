package com.kimbh.simplelogin.ui.compose.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kimbh.core.utils.AuthType
import com.kimbh.simplelogin.R

@Composable
fun LoginContent(
    onLoginClicked: (AuthType) -> Unit,
    facebookLogin: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        SocialLoginButton(
            painter = painterResource(R.drawable.facebook_logo),
            contentDescription = "Facebook login",
            backgroundColor = Color(0xFF1877F2),
            onClick = { facebookLogin() }
        )
        Spacer(modifier = Modifier.width(16.dp))
        SocialLoginButton(
            painter = painterResource(R.drawable.kakao_logo),
            contentDescription = "Kakao login",
            backgroundColor = Color(0xFFFFCD00),
            onClick = { onLoginClicked(AuthType.KAKAO) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        SocialLoginButton(
            painter = painterResource(R.drawable.naver_logo),
            contentDescription = "Naver login",
            backgroundColor = Color(0xFF03C75A),
            onClick = { onLoginClicked(AuthType.NAVER) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        SocialLoginButton(
            painter = painterResource(R.drawable.google_logo),
            contentDescription = "Google login",
            backgroundColor = Color(0xFF4285F4),
            onClick = { onLoginClicked(AuthType.GOOGLE) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginContentPreview() {
    LoginContent({}, {})
}