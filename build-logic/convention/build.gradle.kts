plugins {
    `kotlin-dsl`
}

group = "com.kimbh.myapplication.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "sixkids.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("TestPlugin") {
            id = "a.b.c"
            implementationClass = "TestPlugin"
        }
    }
}
