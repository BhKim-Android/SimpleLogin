plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.auth_kakao"
}

dependencies {
    implementation(project(":simple-login-sdk:core"))
    implementation(project(":simple-login-sdk:data"))

    implementation("com.kakao.sdk:v2-user:2.21.7")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}