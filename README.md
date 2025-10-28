# Simple Login SDK

Android 앱을 위한 간편한 통합 소셜 로그인 SDK입니다. 카카오, 네이버, 구글, 페이스북 로그인을 하나의 인터페이스로 통합하여 관리할 수 있도록 돕습니다.


## ✨ 특징 (Features)

- **통합 API**: `AuthManager`를 통해 여러 소셜 로그인을 일관된 방식으로 호출합니다.
- **간편한 설정**: 각 플랫폼의 앱 키를 `AuthConfig` 객체 하나로 관리합니다.
- **Hilt 지원**: Hilt를 사용하여 SDK 내부 의존성을 관리하며, 앱의 Hilt 그래프와 연동됩니다.

### 지원 플랫폼

- Kakao
- Naver
- Google
- Facebook

---

## 🚀 시작하기 (Getting Started)

### 1. 전제 조건 (Prerequisites)
이 SDK는 Hilt를 사용하여 의존성을 주입합니다. SDK를 사용하는 앱 역시 Hilt를 설정해야 합니다.

- `app/build.gradle.kts`에 Hilt 플러그인 추가
- `@HiltAndroidApp`어노테이션이 달린 `Application` 클래스

### 2. 의존성 설정 (Dependency Setup)

(프로젝트 구조에 따라) `app` 모듈의 `build.gradle.kts` 파일에 SDK 모듈을 추가합니다.

```kotlin
dependencies {
    implementation(project(":simple-login-sdk"))
    
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // ...
}
```

### 3. Gradle 설정 (app/build.gradle.kts)

각 소셜 플랫폼(Kakao, Facebook)에서 요구하는 네이티브 앱 키와 설정값을 `app` 모듈의 `build.gradle.kts`에 추가해야 합니다. 이 값들은 SDK 내부의 `AndroidManifest.xml` 파일에서 사용됩니다.

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

### 4. Application 클래스 설정 (Initialization)

앱이 시작될 때 `Application` 클래스의 `onCreate()` 메소드에서 `AuthManager.initialize`를 호출하여 SDK를 초기화합니다.

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

## 🔧 사용 방법 (Usage Guide)

### 1. 로그인 (Kakao, Naver, Google)

`AuthManager.login()`은 `suspend` 함수이므로 Coroutine 스코프 내에서 호출해야 합니다. (예: `viewModelScope`)

`AuthType`으로 원하는 플랫폼을 지정합니다.

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

### 2. 페이스북 로그인 (Facebook Login)

페이스북 로그인은 `Activity` 컨텍스트가 필요하므로 `AuthManager`가 아닌 `AuthFacebookManager`를 사용해야 합니다.

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

### 3. 유저 정보 가져오기 (Get User Info)

로그인 성공 후 받은 `SdkTokenInfo` 객체를 사용하여 `AuthManager.getUserInfo()`를 호출합니다.

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
