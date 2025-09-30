plugins {
    id("kimbh.android.library")
}
android {
    namespace = "com.kimbh.domain"
}
dependencies {
    implementation(project(":simple-login-sdk:core"))

    implementation(libs.kotlin.coroutine.core)
    implementation(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
}
