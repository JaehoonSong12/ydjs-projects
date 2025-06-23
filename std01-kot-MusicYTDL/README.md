# MyComposeApp

A modern Android application built with Jetpack Compose, Hilt for dependency injection, MVVM architecture, and Navigation-Compose. The project targets API level 30 (Android 11) and is tested on a Pixel 7 (API 33) system image.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Prerequisites](#prerequisites)
3. [Project Structure](#project-structure)
4. [Dependencies](#dependencies)
5. [Configuration](#configuration)
6. [Architecture](#architecture)
7. [Screenshots](#screenshots)
8. [Setup & Run](#setup--run)
9. [Testing](#testing)
10. [Emulator Configuration](#emulator-configuration)
11. [Further Reading](#further-reading)

---

## Project Overview

MyComposeApp demonstrates a best-practice Android setup using:

* **Jetpack Compose** for UI
* **Hilt** for dependency injection
* **MVVM** with `ViewModel` and `StateFlow`
* **Navigation-Compose** for in-app navigation
* A stubbed **Repository** layer (fake implementation)
* Material 3 theming

This template provides a solid foundation for building production-ready, maintainable Compose applications.

---

## Prerequisites

* Android Studio Electric Eel (or later) with Compose support
* Java 11 or higher
* Android SDK Platform 30 installed
* Kotlin 1.8+

---

## Project Structure

```
app/                  # Android application module
 ├─ src/main/java/com/example/mycomposeapp
 │    ├─ MainActivity.kt          # Entry point, sets up NavHost
 │    ├─ MyComposeApp.kt          # Application class with Hilt
 │    ├─ di/                      # Hilt modules
 │    ├─ data/                    # Repository interfaces & implementations
 │    ├─ ui/                      # Compose screens & theming
 │    │    ├─ theme/              # Color, typography, shapes
 │    │    └─ screens/            # HomeScreen, DetailsScreen
 │    └─ navigation/              # Navigation routes & NavHost
 ├─ build.gradle.kts
 └─ AndroidManifest.xml
```

---

## Dependencies

* **Compose UI:** `androidx.compose.ui:ui:1.5.0`
* **Compose Material 3:** `androidx.compose.material3:material3:1.1.0`
* **Activity Compose:** `androidx.activity:activity-compose:1.9.0`
* **Hilt:** `com.google.dagger:hilt-android:2.45` + `hilt-android-compiler`
* **Lifecycle & ViewModel:** `lifecycle-runtime-ktx:2.6.1`, `lifecycle-viewmodel-compose:2.6.1`
* **Navigation-Compose:** `androidx.navigation:navigation-compose:2.7.0`
* **Kotlin Coroutines:** `kotlinx-coroutines-core:1.7.0`, `kotlinx-coroutines-android:1.7.0`

---

## Configuration

* **compileSdk:** 34
* **minSdkVersion:** 28
* **targetSdkVersion:** 30 (Android 11 R distribution)
* **Compose Compiler:** `kotlinCompilerExtensionVersion = "1.5.0"`
* **Hilt Plugin:** Applied in module Gradle file

---

## Architecture

* **MVVM**:

  * `ViewModel` exposes a `StateFlow<UiState>`
  * UI layers use `collectAsState()` to observe state
* **DI**:

  * Hilt provides `CounterRepo` stub via `DataModule`
* **Navigation**:

  * Single-activity with `NavHost` and composable destinations
* **Theming**:

  * Material 3 via `MyComposeAppTheme`

---

## Screenshots

*(Add screenshots of your app here)*

---

## Setup & Run

1. Clone the repo:

   ```bash
   git clone https://github.com/yourusername/MyComposeApp.git
   cd MyComposeApp
   ```
2. Open in Android Studio.
3. Let Gradle sync and download dependencies.
4. Select **app** > **Run**.

---

## Testing

* **Unit Tests**: Add JUnit tests under `src/test` for ViewModels and data.
* **UI Tests**: Use `createAndroidComposeRule` in `src/androidTest` for Compose UI tests.

---

## Emulator Configuration

* **Device:** Pixel 7
* **API Level:** 33 (Android 13)
* **System Image:** x86\_64 Google APIs
* **Graphics:** Hardware accelerated
* **Recommended RAM:** 4 GB+

This setup ensures fast emulator performance and access to Google APIs.

---

## Further Reading

* [Jetpack Compose Codelabs](https://developer.android.com/jetpack/compose/codelabs)
* [Hilt Documentation](https://dagger.dev/hilt/)
* [Navigation-Compose Guide](https://developer.android.com/jetpack/compose/navigation)

---

#### License

[MIT](LICENSE)
