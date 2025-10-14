plugins {
    id("kimbh.android.application")
    id("kimbh.android.application.compose")
    id("kimbh.android.hilt")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.kimbh.simplelogin"

    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

    defaultConfig {
        applicationId = "com.kimbh.simplelogin"
        versionCode = 1
        versionName = "1.0"

        // 카카오 네이티브 앱 키를 변수에 저장하여 재사용
        val kakaoNativeAppKey = "821cb821d1ae2781c9839f9e390421f7"
        // Facebook..
        val facebookAppId = "2200975527076275"
        val facebookClientToken = "9cd4352ab1dfe183943a08480079f88d"
        val fbLoginScheme = "fb123456789"

        // [Kakao SDK 설정]
        // SDK 초기화 및 Manifest Placeholder에 사용될 네이티브 앱 키를 정의합니다.
        // resValue: 앱 리소스에 문자열 값으로 저장합니다. (필요에 따라 활용)
        resValue("string", "kakao_native_app_key", kakaoNativeAppKey)
        // Facebook
        resValue("string", "facebook_app_id", facebookAppId)
        resValue("string", "facebook_client_token", facebookClientToken)
        resValue("string", "fb_login_protocol_scheme", fbLoginScheme)

        // manifestPlaceholders: 하위 모듈(sdk-auth)의 AndroidManifest.xml에서
        // `${kakao_native_app_key}` 변수를 이 값으로 대체하도록 설정합니다.
        // 이를 통해 앱 키를 한 곳에서만 관리하고, 여러 모듈에서 재사용할 수 있습니다.
        manifestPlaceholders["kakao_native_app_key"] = kakaoNativeAppKey
        // Facebook
        manifestPlaceholders["FACEBOOK_APP_ID"] = facebookAppId
        manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = facebookClientToken
        manifestPlaceholders["FB_LOGIN_PROTOCOL"] = fbLoginScheme
    }
}

dependencies {
    implementation(project(":simple-login-sdk"))
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.toolingPreview)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.tooling)
    debugImplementation(libs.androidx.compose.uiTestManifest)
}