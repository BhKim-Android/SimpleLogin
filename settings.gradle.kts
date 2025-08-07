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
include(":core-android")
include(":data")
include(":domain")
include(":feature-auth")
include(":ui-auth")
include(":sdk-auth")
include(":core")
