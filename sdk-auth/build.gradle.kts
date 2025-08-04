plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.sdk_auth"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"your_kakao_native_key_here\"")
    }
}

dependencies {

    implementation("com.kakao.sdk:v2-user:2.11.0")
}