# DXP Mobile Mini App Template

A **Kotlin Multiplatform (KMP)** template for creating mini-app modules targeting Android and iOS using Compose Multiplatform.

## 🎯 Purpose

Use this template to quickly create new mini-app repositories in the DXP Mobile ecosystem with automatic module setup.

## 🚀 Quick Start

### 1. Create Repository

1. Click **"Use this template"** on GitHub
2. Name it: `dxp-mobile-<module-name>` (e.g., `dxp-mobile-ecommerce`)
3. Create the repository

### 2. Automatic Setup

The CI workflow automatically creates:
- Complete module structure
- Platform-specific files (Android/iOS)
- Build configuration
- Updated README with your module name

### 3. Configure & Build

Clone and set up credentials in `gradle.properties`:

```properties
gpr.user=YOUR_GITHUB_USERNAME
gpr.key=YOUR_GITHUB_PERSONAL_ACCESS_TOKEN
```

**⚠️ Never commit credentials to the repository**

## 📛 Naming Convention (Required)

Repository name **must** follow: `dxp-mobile-<module-name>`

**Examples:**
- `dxp-mobile-ecommerce` → Module: `ecommerce`
- `dxp-mobile-payment` → Module: `payment`
- `dxp-mobile-loyalty` → Module: `loyalty`

## 📁 Generated Structure

After CI completes:

```
your-module-name/
├── src/
│   ├── commonMain/kotlin/com/maf/your-module/
│   ├── androidMain/kotlin/com/maf/your-module/
│   └── iosMain/kotlin/com/maf/your-module/
└── build.gradle.kts
```

## 📚 Resources

- [Kotlin Multiplatform Docs](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)

## 🔧 How It Works

The CI workflow (`.github/workflows/setup-module.yml`):
- Runs once on first push
- Extracts module name from repository name
- Creates complete KMP module structure
- Auto-commits the setup

---

**Ready to start?** Click "Use this template" and follow the naming convention!