package com.kimbh.simplelogin.ui.compose.userinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kimbh.core.model.UserInfoGender
import com.kimbh.core.utils.AuthType
import com.kimbh.simple_login_sdk.model.SdkUserInfo

@Composable
fun UserInfoContent(
    modifier: Modifier,
    sdkUserInfo: SdkUserInfo
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Item(title = "AuthType", value = sdkUserInfo.authType.name)
            Item(title = "id", value = sdkUserInfo.id)
            Item(title = "nickName", value = sdkUserInfo.nickName ?: "")
            Item(title = "profileImage", value = sdkUserInfo.profileImage ?: "")
            Item(title = "gender", value = sdkUserInfo.gender?.name ?: "MALE")
            Item(title = "birthday", value = sdkUserInfo.birthday ?: "")
        }
    }
}

@Composable
private fun Item(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "$title : ",
            color = Color.Gray,
            fontSize = 15.sp
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
fun UserInfoContentPreview() {
    UserInfoContent(
        modifier = Modifier,
        sdkUserInfo = SdkUserInfo(
            authType = AuthType.FACEBOOK,
            id = "",
            email = "",
            nickName = "",
            profileImage = "",
            gender = UserInfoGender.MALE,
            birthday = ""
        )
    )
}