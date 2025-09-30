pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }

        includeBuild("build-logic")
    }
}
rootProject.name = "SimpleLogin"
include(":app")
include(":simple-login-sdk")
include(":simple-login-sdk:domain")
include(":simple-login-sdk:data")
include(":simple-login-sdk:auth-kakao")
include(":simple-login-sdk:core")
include(":simple-login-sdk:auth-naver")
include(":simple-login-sdk:auth-facebook")
include(":simple-login-sdk:auth-google")
