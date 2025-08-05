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

        // [Kakao SDK 설정]
        // SDK 초기화 및 Manifest Placeholder에 사용될 네이티브 앱 키를 정의합니다.
        // resValue: 앱 리소스에 문자열 값으로 저장합니다. (필요에 따라 활용)
        resValue("string", "kakao_native_app_key", "821cb821d1ae2781c9839f9e390421f7")

        // manifestPlaceholders: 하위 모듈(sdk-auth)의 AndroidManifest.xml에서
        // `${kakao_native_app_key}` 변수를 이 값으로 대체하도록 설정합니다.
        // 이를 통해 앱 키를 한 곳에서만 관리하고, 여러 모듈에서 재사용할 수 있습니다.
        manifestPlaceholders["kakao_native_app_key"] = "821cb821d1ae2781c9839f9e390421f7"
    }
}

dependencies {
    implementation(project(":core-android"))
    implementation(project(":sdk-auth"))
}