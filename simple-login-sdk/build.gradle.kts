plugins {
    id("kimbh.android.application")
    id("kimbh.android.hilt")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.kimbh.simple_login_sdk"
}

dependencies {
    implementation(project(":simple-login-sdk:core"))
}