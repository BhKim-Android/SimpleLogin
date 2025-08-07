plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}
android {
    namespace = "com.kimbh.domain"
}

dependencies {
    implementation(project(":core"))
}
