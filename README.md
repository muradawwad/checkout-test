# checkout-test

A Kotlin Multiplatform (KMP) mini-app module for Android and iOS platforms using Compose Multiplatform.

## 📦 Module Information

- **Module Name:** `checkout-test`
- **Repository:** `checkout-test`
- **Package:** `com.maf.checkout-test`

## 🏗️ Project Structure

```
checkout-test/
├── src/
│   ├── commonMain/
│   │   ├── kotlin/com/maf/checkout-test/
│   │   │   └── Platform.kt
│   │   └── composeResources/values/
│   │       └── strings.xml
│   ├── androidMain/
│   │   └── kotlin/com/maf/checkout-test/
│   │       └── Platform.android.kt
│   └── iosMain/
│       └── kotlin/com/maf/checkout-test/
│           └── Platform.ios.kt
└── build.gradle.kts
```

## 🚀 Getting Started

### Prerequisites

Configure your GitHub credentials in `gradle.properties`:

```properties
gpr.user=YOUR_GITHUB_USERNAME
gpr.key=YOUR_GITHUB_PERSONAL_ACCESS_TOKEN
```

**⚠️ Important:** Never commit credentials. Use environment variables or GitHub Secrets for CI/CD.

### Build the Project

```bash
./gradlew build
```

### Run on Android

```bash
./gradlew :composeApp:installDebug
```

### Run on iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and run.

## 📚 Included SDKs

This project includes pre-configured SDK modules:

- **Network SDK** - HTTP client, serialization, error handling
- **SharedUI SDK** - Reusable UI components, themes, and utilities

## 🔧 Development

Start building your mini-app features in:
- `checkout-test/src/commonMain/` - Shared code
- `checkout-test/src/androidMain/` - Android-specific code
- `checkout-test/src/iosMain/` - iOS-specific code

## 📖 Documentation

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)

## 📝 Notes

This module was automatically generated from the `dxp-mobile-miniapp` template repository.