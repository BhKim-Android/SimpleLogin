plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.sdk_auth"

    defaultConfig {
        // IDE 경고를 없애기 위한 임시 값입니다.
        manifestPlaceholders["kakao_native_app_key"] = ""
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation("com.kakao.sdk:v2-user:2.11.0")
}