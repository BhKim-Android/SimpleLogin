plugins {
    id("kimbh.android.library")
}
dependencies {
    implementation(libs.kotlin.coroutine.core)
    implementation(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
}
