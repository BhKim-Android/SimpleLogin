plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.kimbh.simple_login_sdk"
}

dependencies {
    implementation(project(":simple-login-sdk:domain"))

    api(project(":simple-login-sdk:core"))
    implementation(project(":simple-login-sdk:auth-kakao"))
    implementation(project(":simple-login-sdk:auth-naver"))
    implementation(project(":simple-login-sdk:auth-facebook"))
    implementation(project(":simple-login-sdk:auth-google"))
}