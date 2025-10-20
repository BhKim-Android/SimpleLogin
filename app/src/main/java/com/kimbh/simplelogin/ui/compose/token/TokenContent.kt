package com.kimbh.simplelogin.ui.compose.token

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.model.SdkTokenInfo

@Composable
fun TokenContent(
    sdkTokenInfo: SdkTokenInfo,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = sdkTokenInfo.authType.name,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "accessToken : ${sdkTokenInfo.accessToken}"
        )
        Text(
            text = "newToken : ${sdkTokenInfo.newToken}"
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onClick, modifier = Modifier) {
            Text(text = "유저정보 요청")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TokenContentPreview() {
    TokenContent(
        sdkTokenInfo = SdkTokenInfo(
            AuthType.KAKAO,
            accessToken = "PreviewAccessToekn",
            newToken = "PreviewNewToken"
        ),
        onClick = {}
    )
}