plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.auth_naver"
}

dependencies {
    implementation(project(":simple-login-sdk:core"))
    implementation(project(":simple-login-sdk:data"))

    implementation("com.navercorp.nid:oauth:5.10.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}