import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.kotlin.serialization)
    id("maven-publish")
}

group = "com.maf.checkout-test"
version = "1.0.0"

val generateVersionFile by tasks.registering {
    val outputDir = file("${layout.buildDirectory.get()}/generated/source/buildConfig")
    val projectVersion = version.toString()
    val projectGroup = group.toString()
    val X_API_KEY_DEV = ""
    val X_API_KEY_PROD = ""

    outputs.dir(outputDir)

    doLast {
        val file = File(outputDir, "com/maf/checkouttest/BuildConfig.kt")
        file.parentFile.mkdirs()
        file.writeText("""
            package com.maf.checkouttest
            
            object BuildConfig {
                const val VERSION = "$projectVersion"
                const val GROUP = "$projectGroup"
                const val X_API_KEY_DEV = "$X_API_KEY_DEV"
                const val X_API_KEY_PROD = "$X_API_KEY_PROD"
            }
        """.trimIndent())
    }
}

kotlin {
    androidLibrary {
        namespace = "com.maf.checkouttest"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    val xcfName = "checkout-test"
    val xcf = XCFramework(xcfName)

    val iosTargets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())
    iosTargets.forEach { t ->
        t.binaries.framework {
            baseName = xcfName
            isStatic = false
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain {
            kotlin.srcDir(tasks.named("generateVersionFile"))
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlin.test)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                implementation("com.maf.core:networking:1.1.5-beta")
                implementation("com.maf.sharedui:sharedui:1.4.67-beta")
                implementation("com.maf.mafcheckout:mafcheckout:1.0.32-dev")
                implementation(libs.jetbrains.compose.navigation)
                implementation(libs.androidx.lifecycle.viewmodel)

                implementation("androidx.datastore:datastore:1.1.7")
                implementation("androidx.datastore:datastore-preferences:1.1.7")

                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.core)
            }
        }

        commonTest {
            dependencies {

            }
        }

        androidMain {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.androidx.compose)
                implementation("androidx.browser:browser:1.8.0")
                implementation("androidx.startup:startup-runtime:1.2.0")
                implementation("com.google.android.material:material:1.13.0")
            }
        }


        iosMain {
            dependencies {

            }
        }
    }
}

compose.resources {
    publicResClass = true
    customDirectory(
        sourceSetName = "commonMain",
        directoryProvider = provider { layout.projectDirectory.dir("src/commonMain/composeResources") }
    )
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/majidalfuttaim/dxp-mobile-checkout-test")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull
                    ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.withType<PublishToMavenRepository>().configureEach {
    val pub = publication
    if (pub is MavenPublication) {
        pub.artifactId = when (pub.name) {
            "kotlinMultiplatform" -> "checkout-test"
            "android" -> "checkout-test-android"
            "iosArm64" -> "checkout-test-iosarm64"
            "iosX64" -> "checkout-test-iosx64"
            "iosSimulatorArm64" -> "checkout-test-iossimulatorarm64"
            else -> "checkout-test-${pub.name}"
        }
    }
}

// The SDK is compiled against this ShareUI line; a newer major breaks its v2 component symbols.
configurations.configureEach {
    resolutionStrategy {
        force("com.maf.sharedui:sharedui:1.4.67-beta")
        force("com.maf.core:networking:1.1.5-beta")
    }
}
