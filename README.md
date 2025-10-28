# Simple Login SDK

Android 앱을 위한 간편한 통합 소셜 로그인 SDK입니다.  
카카오, 네이버, 구글, 페이스북 로그인을 하나의 인터페이스로 통합하여 관리할 수 있습니다.

---

## Features

- **통합 API**: `AuthManager`를 통해 여러 소셜 로그인을 일관된 방식으로 호출
- **간편한 설정**: 각 플랫폼의 앱 키를 `AuthConfig` 객체 하나로 관리
- **Hilt 지원**: SDK 내부 의존성을 Hilt로 관리하며, 앱 Hilt 그래프와 연동

### 지원 플랫폼

- Kakao
- Naver
- Google
- Facebook

---

## Getting Started

### 1. Prerequisites

- 앱과 SDK 모두 Hilt 설정 필요
- `@HiltAndroidApp`이 달린 Application 클래스 필요

---

### 2. Dependency Setup

`app/build.gradle.kts`에 SDK 모듈 추가:

```kotlin
dependencies {
    implementation(project(":simple-login-sdk"))
    
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
```

---

### 3. Gradle 설정

```kotlin
android {
    defaultConfig {
        val kakaoNativeAppKey = "YOUR_KAKAO_NATIVE_APP_KEY"
        resValue("string", "kakao_native_app_key", kakaoNativeAppKey)
        manifestPlaceholders["kakao_native_app_key"] = kakaoNativeAppKey

        val facebookAppId = "YOUR_FACEBOOK_APP_ID"
        val facebookClientToken = "YOUR_FACEBOOK_CLIENT_TOKEN"
        val fbLoginScheme = "fb${facebookAppId}"
        
        resValue("string", "facebook_app_id", facebookAppId)
        resValue("string", "facebook_client_token", facebookClientToken)
        resValue("string", "fb_login_protocol_scheme", fbLoginScheme)
        
        manifestPlaceholders["FACEBOOK_APP_ID"] = facebookAppId
        manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = facebookClientToken
        manifestPlaceholders["FB_LOGIN_PROTOCOL"] = fbLoginScheme
    }
}
```

---

### 4. Application 클래스 설정

```kotlin
@HiltAndroidApp
class SimpleLoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        val authConfig = AuthConfig(
            platformConfigs = mapOf(
                AuthType.KAKAO to KakaoAuthConfig(appkey = "YOUR_KAKAO_NATIVE_APP_KEY"),
                AuthType.NAVER to NaverAuthConfig(
                    clientId = "YOUR_NAVER_CLIENT_ID",
                    clientSecret = "YOUR_NAVER_CLIENT_SECRET",
                    clientName = "YOUR_NAVER_CLIENT_NAME"
                ),
                AuthType.FACEBOOK to FacebookAuthConfig,
                AuthType.GOOGLE to GoogleAuthConfig(serverClientId = "YOUR_GOOGLE_SERVER_CLIENT_ID")
            )
        )
        
        AuthManager.initialize(context = this, authConfig = authConfig)
    }
}
```

---

## Usage

### 1. 로그인 (Kakao, Naver, Google)

```kotlin
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _tokenState = MutableStateFlow<UiState<SdkTokenInfo>>(UiState.Loading)
    val tokenState: StateFlow<UiState<SdkTokenInfo>> = _tokenState

    fun onLoginClicked(authType: AuthType) = viewModelScope.launch {
        _tokenState.value = UiState.Loading
        
        AuthManager.login(authType = authType)
            .onSuccess { sdkTokenInfo ->
                _tokenState.value = UiState.Success(sdkTokenInfo)
            }
            .onFailure { error ->
                _tokenState.value = UiState.Error(error.message)
            }
    }
}
```

---

### 2. Facebook 로그인

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private fun handleFacebookLogin() {
        lifecycleScope.launch {
            viewModel.bindFacebookTokenState(UiState.Loading)
            
            val result = AuthFacebookManager.login(this@MainActivity)
            
            result.onSuccess { sdkTokenInfo ->
                viewModel.bindFacebookTokenState(UiState.Success(sdkTokenInfo))
            }.onFailure { error ->
                viewModel.bindFacebookTokenState(UiState.Error(error.message))
            }
        }
    }
}
```

Compose UI 예시:

```kotlin
SocialLoginButton(
    onClick = { facebookLogin() }
)
```

---

### 3. 유저 정보 가져오기

```kotlin
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _userInfoState = MutableStateFlow<UiState<SdkUserInfo>>(UiState.Loading)
    val userInfoState: StateFlow<UiState<SdkUserInfo>> = _userInfoState

    private var lastAuthType: AuthType? = null

    fun onLoginClicked(authType: AuthType) = viewModelScope.launch {
        _tokenState.value = UiState.Loading
        lastAuthType = authType
        
        AuthManager.login(authType = authType)
            .onSuccess { sdkTokenInfo ->
                _tokenState.value = UiState.Success(sdkTokenInfo)
                getUserInfo(sdkTokenInfo)
            }
            .onFailure { error ->
                _tokenState.value = UiState.Error(error.message)
            }
    }

    fun getUserInfo(sdkTokenInfo: SdkTokenInfo) = viewModelScope.launch {
        _userInfoState.value = UiState.Loading
        lastAuthType?.let { authType ->
            AuthManager.getUserInfo(authType = authType, sdkTokenInfo = sdkTokenInfo)
                .onSuccess { sdkUserInfo ->
                    _userInfoState.value = UiState.Success(sdkUserInfo)
                }
                .onFailure { error ->
                    _userInfoState.value = UiState.Error(error.message)
                }
        }
    }
}
```
