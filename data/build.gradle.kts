plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

android {
    namespace = "com.kimbh.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":sdk-auth"))

    implementation(project(":core"))
}
