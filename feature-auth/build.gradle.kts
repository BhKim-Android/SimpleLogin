plugins {
    id("kimbh.android.application")
    id("kimbh.android.application.compose")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.feature_auth"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
}