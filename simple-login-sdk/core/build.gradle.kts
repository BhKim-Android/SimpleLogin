plugins {
    id("kimbh.android.library")
}
android {
    namespace = "com.kimbh.core"
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
}
