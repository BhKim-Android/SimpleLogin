plugins {
    id("kimbh.android.application")
    id("kimbh.android.application.compose")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.simplelogin"

    defaultConfig {
        applicationId = "com.kimbh.simplelogin"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core-android"))
    implementation(project(":sdk-auth"))
}