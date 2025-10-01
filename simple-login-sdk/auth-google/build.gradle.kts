plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.auth_google"
}

dependencies {
    implementation(project(":simple-login-sdk:core"))
    implementation(project(":simple-login-sdk:data"))

    implementation("androidx.credentials:credentials:1.6.0-alpha05")
    implementation("androidx.credentials:credentials-play-services-auth:1.6.0-alpha05")
    implementation("com.google.android.libraries.identity.googleid:googleid:<latest version>")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}