# 🚀 SimpleLogin – Android 간편 로그인 예제

이 프로젝트는 **안드로이드에서 간편 로그인(OAuth2)** 기능을 구현한 학습용 앱입니다.  
**Kakao / Google / Naver** 등의 로그인 방식을 통합하고, **MVVM + Clean Architecture + Multi-Module** 구조로 구성되어 있습니다.

---

## SDK Appkey 설정.

```kotlin
android {
    defaultConfig {
        resValue "string", "kakao_native_app_key", "여기에_앱의_네이티브_앱_키를_입력하세요"
    }
}
```

---

## 🧩 주요 기능

- ✅ 카카오, 구글, 네이버 간편 로그인 구현
- ✅ 사용자 정보 조회 (이름, 이메일 등)
- ✅ Jetpack Compose UI 기반
- ✅ 멀티 모듈 구조로 분리 및 유지보수 용이성 확보
- ✅ 클린 아키텍처 기반의 관심사 분리
- ✅ 향후 기능 확장 가능 (Apple Login, Firebase Auth 등)

---

## 📁 모듈 구조

<pre>SimpleLogin/
├── app # 앱 진입점 및 네비게이션, DI 설정
├── core-android # 공통 Android 유틸, 확장 함수, 테마 등
├── data # 데이터 소스(API, DB 등) 구현
├── domain # 비즈니스 로직(UseCase, Entity, Repository 인터페이스)
├── auth-sdk # 간편 로그인 SDK 래핑 모듈 (재사용 라이브러리)
├── feature-auth # 로그인 화면 상태 관리(ViewModel 등), 비즈니스 흐름
├── ui-auth # Stateless Compose UI 컴포넌트 (재사용 가능 UI)</pre>


## 개발일지 및 공부
https://www.notion.so/197388df547e80ed902aca2d4fd1662f?v=246388df547e80e6bb93000cdea98cbf
