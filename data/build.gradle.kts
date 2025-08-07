plugins {
    id("kimbh.android.library")
    id("kimbh.android.hilt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":sdk-auth"))

    implementation(project(":core"))
}
