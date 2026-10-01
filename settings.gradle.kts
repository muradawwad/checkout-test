rootProject.name = "MiniApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        listOf(
            "https://maven.pkg.github.com/majidalfuttaim/dxp-mobile-core",
            "https://maven.pkg.github.com/majidalfuttaim/dxp-mobile-uikit",
        ).forEach { repoUrl ->
            maven {
                url = uri(repoUrl)
                credentials {
                    username = providers.gradleProperty("gpr.user").orNull
                        ?: System.getenv("GITHUB_ACTOR")
                    password = providers.gradleProperty("gpr.key").orNull
                        ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupAndSubgroups("com.github")
            }
        }
        maven {
            url = uri("https://maven.fpregistry.io/releases")
            content {
                includeGroupAndSubgroups("com.fingerprint")
            }
        }
    }
}



include(":composeApp")
include(":checkout-test")
