# Simple Login SDK

Android 앱을 위한 간편한 통합 소셜 로그인 SDK입니다. 각 소셜 플랫폼(카카오, 네이버, 구글, 페이스북)의 복잡한 SDK 설정과 구현을 추상화하여, `AuthManager`라는 단일 인터페이스를 통해 간편하게 로그인을 구현할 수 있도록 돕습니다.

## 🌟 핵심 목표 (Core Purpose)

이 SDK의 핵심 목표는 **플랫폼 의존성 제거**입니다. 개발자가 카카오, 네이버 등 개별 SDK의 초기화 방법, 로그인 호출 방식, 콜백 처리를 몰라도 `AuthManager.login()`이라는 하나의 함수로 모든 소셜 로그인을 처리할 수 있게 하는 것이 목적입니다.

## ✨ 주요 기능 (Features)


- **🏛️ 단일 인터페이스**: `AuthManager`로 카카오, 네이버, 구글 로그인 및 유저 정보 조회를 한번에 처리합니다.
- **📱 페이스북 특별 지원**: `Activity` 의존성이 있는 페이스북 로그인을 위한 별도 `AuthFacebookManager`를 제공합니다.
- **⚙️ 간편한 설정**: Hilt를 통한 의존성 주입 및 `AuthConfig` 객체 하나로 모든 플랫폼의 키를 관리합니다.
- **📦 모듈화**: `core`, `domain`, 각 `auth-***` 플랫폼별 모듈로 분리되어 있습니다.

---

### 지원 플랫폼

- Kakao
- Naver
- Google
- Facebook

---

## 🚀 설치 (Installation)
[안내] 이 섹션은 SDK가 **JitPack**을 통해 배포된다고 가정한 예시입니다. 만약 Maven Central이나 다른 방식으로 배포하신다면, 그에 맞게 수정해야 합니다.

### 1. `settings.gradle.kts` (Project 루트) 에 저장소 추가

```kotlin
dependencyResolutionManagement {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. `build.gradle.kts` (App 모듈) 에 의존성 추가

```kotlin
dependencies {
    // 'VERSION' 에는 GitHub에 릴리즈된 태그명을 입력합니다. (예: "1.0.0")
    implementation("com.github.BhKim-Android:SimpleLogin:VERSION")

    // Hilt는 사전에 설정되어 있어야 합니다.
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
```

---

## 🛠️ 사용 설정 (Setup Guide)

SDK를 사용하기 위해 앱에서 몇 가지 초기 설정이 필요합니다.

### 1. Hilt 설정

이 SDK는 Hilt를 기반으로 동작합니다. SDK를 사용하는 **Application 클래스**와 **Activity**에 `@HiltAndroidApp` 및 `@AndroidEntryPoint` 어노테이션이 반드시 설정되어 있어야 합니다.

```kotlin
// App
@HiltAndroidApp
class SimpleLoginApplication : Application() { ... }

// Activity
@AndroidEntryPoint
class MainActivity : ComponentActivity() { ... }
```

### 2. Gradle 설정 (app/build.gradle.kts)

카카오, 페이스북 SDK는 `AndroidManifest.xml`에 네이티브 앱 키 등을 요구합니다. SDK가 이 값들을 참조할 수 있도록 `app` 모듈의 `build.gradle.kts`에 다음과 같이 `manifestPlaceholders를 설정해야 합니다.

```kotlin
// app/build.gradle.kts
android {
    defaultConfig {
        // ...

        // 1. 카카오 네이티브 앱 키
        val kakaoNativeAppKey = "YOUR_KAKAO_NATIVE_APP_KEY"
        resValue("string", "kakao_native_app_key", kakaoNativeAppKey)
        manifestPlaceholders["kakao_native_app_key"] = kakaoNativeAppKey

        // 2. Facebook 앱 ID 및 토큰
        val facebookAppId = "YOUR_FACEBOOK_APP_ID"
        val facebookClientToken = "YOUR_FACEBOOK_CLIENT_TOKEN"
        val fbLoginScheme = "fb${facebookAppId}" // "fb" 접두사 필수

        resValue("string", "facebook_app_id", facebookAppId)
        resValue("string", "facebook_client_token", facebookClientToken)
        resValue("string", "fb_login_protocol_scheme", fbLoginScheme)

        manifestPlaceholders["FACEBOOK_APP_ID"] = facebookAppId
        manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = facebookClientToken
        manifestPlaceholders["FB_LOGIN_PROTOCOL"] = fbLoginScheme
    }
}
```

### 3. SDK 초기화 (`Application` 클래스)

앱이 시작될 때 `Application` 클래스의 `onCreate()`에서 `AuthManager.initialize`를 호출하여 각 플랫폼의 키를 전달하고 SDK를 초기화합니다.

```kotlin
// SimpleLoginApplication.kt

@HiltAndroidApp
class SimpleLoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // 1. 각 플랫폼의 설정값을 AuthConfig 객체로 구성
        val authConfig = AuthConfig(
            platformConfigs = mapOf(
                AuthType.KAKAO to KakaoAuthConfig(appkey = "YOUR_KAKAO_NATIVE_APP_KEY"),
                AuthType.NAVER to NaverAuthConfig(
                    clientId = "YOUR_NAVER_CLIENT_ID",
                    clientSecret = "YOUR_NAVER_CLIENT_SECRET",
                    clientName = "YOUR_NAVER_CLIENT_NAME" // 예: "kimbhSimpleLogin"
                ),
                AuthType.GOOGLE to GoogleAuthConfig(serverClientId = "YOUR_GOOGLE_SERVER_CLIENT_ID"),
                AuthType.FACEBOOK to FacebookAuthConfig // 페이스북은 Gradle 설정값을 사용
            )
        )
        
        // 2. AuthManager 초기화
        AuthManager.initialize(context = this, authConfig = authConfig)
    }
}
```
---

## 💻 사용 방법 (Usage Guide)

설정이 완료되면 `AuthManager`를 통해 모든 기능을 사용할 수 있습니다.

### 1. 로그인 (Kakao, Naver, Google)

`AuthManager.login()`은 `suspend` 함수이며, `AuthType`을 파라미터로 받아 해당 플랫폼의 로그인을 수행합니다. `viewModelScope` 등 Coroutine 내에서 호출해야 합니다.

```kotlin
// MainViewModel.kt (예시)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    
    // ...
    private var lastAuthType: AuthType? = null

    fun onLoginClicked(authType: AuthType) = viewModelScope.launch {
        // 로딩 상태 UI 처리
        
        lastAuthType = authType // 유저 정보 조회를 위해 마지막 로그인 타입 저장
        
        AuthManager.login(authType = authType)
            .onSuccess { sdkTokenInfo ->
                // 로그인 성공: sdkTokenInfo (accessToken 등 포함)
                // 이어서 유저 정보 조회 호출
                getUserInfo(sdkTokenInfo) 
            }
            .onFailure { error ->
                // 로그인 실패 처리
            }
    }
}
```
### 2. 페이스북 로그인 (Facebook Login - 예외)

페이스북 SDK는 로그인 결과를 `Activity`의 `onActivityResult`(또는 콜백 매니저)를 통해 받아야 하는 **플랫폼 의존성**이 있습니다. 따라서 `AuthManager`가 아닌 `AuthFacebookManager`를 사용해야 하며, `Activity` 컨텍스트를 직접 전달해야 합니다.

```kotlin
// MainActivity.kt (예시)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ...
            SimpleLoginNavigation(
                // ...
                facebookLogin = { handleFacebookLogin() }
            )
        }
    }
    
    // Facebook 로그인 전용 함수
    private fun handleFacebookLogin() {
        lifecycleScope.launch {
            // 1. ViewModel에 로딩 상태 전달
            viewModel.bindFacebookTokenState(UiState.Loading) 
            
            // 2. AuthFacebookManager로 로그인 호출 (Activity 전달)
            val result = AuthFacebookManager.login(this@MainActivity) 
            
            // 3. 결과에 따라 ViewModel 상태 업데이트
            result.onSuccess { sdkTokenInfo ->
                viewModel.bindFacebookTokenState(UiState.Success(sdkTokenInfo))
            }.onFailure { error ->
                viewModel.bindFacebookTokenState(UiState.Error(error.message))
            }
        }
    }
}
```

### 3. 유저 정보 가져오기 (모든 플랫폼 공통)

로그인 성공 후 받은 `SdkTokenInfo`와 `AuthType`을 `AuthManager.getUserInfo()`에 전달하여 유저 정보를 조회합니다.

```kotlin
// MainViewModel.kt (예시)

    fun getUserInfo(sdkTokenInfo: SdkTokenInfo) = viewModelScope.launch {
        // 로딩 상태 UI 처리
        
        lastAuthType?.let { authType ->
            AuthManager.getUserInfo(authType = authType, sdkTokenInfo = sdkTokenInfo)
                .onSuccess { sdkUserInfo ->
                    // 유저 정보 조회 성공: sdkUserInfo (id, email, nickName 등 포함)
                }
                .onFailure { error ->
                    // 유저 정보 조회 실패 처리
                }
        }
    }
```
---

# 🗒️ 개발 일지 (Development Log)

본 SDK를 개발하며 겪은 과정과 기술적 결정 사항들은 아래 노션 페이지에 기록되어 있습니다.

[➡️ SimpleLogin SDK 개발 일지 (Notion)](https://www.notion.so/SDK-26f388df547e80139a2cc85bc159df40)

