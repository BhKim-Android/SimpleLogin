plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.kimbh.data_remote"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":simple-login-sdk:core"))
    implementation(project(":simple-login-sdk:auth-google"))

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Kotlin Serialization (Google 권장)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Retrofit + kotlinx.serialization Converter
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}