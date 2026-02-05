# VirtualWorld - Quick Setup Guide

## 🚀 Quick Start

This guide will help you get VirtualWorld up and running in under 5 minutes.

## Prerequisites

Before you begin, ensure you have the following installed:

- ✅ **Android Studio** (Hedgehog 2023.1.1 or newer)
- ✅ **JDK 17** or newer
- ✅ **Android SDK** with:
  - SDK Platform 34 (Android 14)
  - SDK Build-Tools 34.0.0
  - Android Emulator or physical device

## Step 1: Clone the Repository

```bash
git clone <repository-url>
cd virtualworld
```

## Step 2: Open in Android Studio

1. Launch Android Studio
2. Select **File → Open**
3. Navigate to the cloned project directory
4. Click **OK**
5. Wait for Gradle sync to complete (first time may take 5-10 minutes)

## Step 3: Firebase Configuration (Required)

### 3.1 Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **Add project** or select existing project
3. Follow the setup wizard

### 3.2 Add Android App

1. In Firebase Console, click **Add app** → **Android**
2. Enter package name: `com.virtualworld.app`
3. Download `google-services.json`
4. Place it in: `app/google-services.json`

### 3.3 Enable Firebase Services

In Firebase Console, enable:
- ✅ **Authentication** → Email/Password, Google, Facebook
- ✅ **Cloud Firestore** → Create database (Start in test mode)
- ✅ **Cloud Storage** → Create bucket
- ✅ **Cloud Messaging** → Enable
- ✅ **Analytics** → Enable

## Step 4: Configure API Endpoint

1. Open `app/src/main/java/com/virtualworld/app/di/AppModule.kt`
2. Update the base URL:

```kotlin
fun provideRetrofit(...): Retrofit = Retrofit.Builder()
    .baseUrl("YOUR_API_URL_HERE")  // Change this
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
```

### For Testing Without Backend

You can use a mock server or:
- **Option 1**: Use `https://jsonplaceholder.typicode.com/` (public test API)
- **Option 2**: Use `http://10.0.2.2:8080/` (local server from Android Emulator)
- **Option 3**: Set up [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) for tests

## Step 5: Build and Run

### Using Android Studio

1. Click **Build → Make Project** (or press Ctrl+F9 / Cmd+F9)
2. Select device/emulator from dropdown
3. Click **Run** (green play button) or press Shift+F10 / Ctrl+R

### Using Command Line

```bash
# Debug build
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Run tests
./gradlew test
```

## Step 6: Test Credentials (Development)

For initial testing, you can use these approaches:

### Option 1: Manual Registration
1. Run the app
2. Click **Register**
3. Fill in the form with test data
4. Submit (will fail without backend, but tests the UI)

### Option 2: Mock Auth (For UI Testing)
You can temporarily modify `AuthRepository.kt` to return mock data:

```kotlin
suspend fun login(email: String, password: String): Flow<Resource<User>> = flow {
    emit(Resource.Loading())
    // Mock success for testing
    val mockUser = User(
        id = "test_user_123",
        email = email,
        username = "testuser",
        displayName = "Test User",
        level = 5,
        experiencePoints = 1250,
        virtualCurrency = 500
    )
    emit(Resource.Success(mockUser))
}
```

## Troubleshooting

### Issue: Gradle Sync Failed

**Solution:**
```bash
# Clean and rebuild
./gradlew clean build

# Or in Android Studio
Build → Clean Project
Build → Rebuild Project
```

### Issue: Firebase Not Working

**Solutions:**
1. Verify `google-services.json` is in `app/` directory
2. Check package name matches: `com.virtualworld.app`
3. Ensure all Firebase services are enabled
4. Sync Gradle again

### Issue: Compilation Errors

**Solutions:**
1. Update Android Studio to latest stable version
2. Check JDK version: File → Project Structure → SDK Location
3. Invalidate caches: File → Invalidate Caches / Restart
4. Check Kotlin version in `build.gradle.kts`

### Issue: Emulator Not Starting

**Solutions:**
1. Check hardware acceleration is enabled (HAXM/KVM)
2. Try creating a new AVD with different API level
3. Increase emulator RAM in AVD settings
4. Use a physical device instead

### Issue: App Crashes on Launch

**Solutions:**
1. Check Logcat for error messages (View → Tool Windows → Logcat)
2. Verify all dependencies are correctly configured
3. Ensure minimum SDK version matches device (min: API 26)
4. Check Firebase configuration is correct

## Project Structure Overview

```
VirtualWorld/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/virtualworld/app/
│   │   │   ├── data/               # Data layer
│   │   │   ├── di/                 # Dependency injection
│   │   │   ├── presentation/       # UI layer
│   │   │   └── util/               # Utilities
│   │   └── res/                    # Resources
│   ├── build.gradle.kts            # App-level Gradle config
│   └── google-services.json        # Firebase config (add this!)
├── build.gradle.kts                # Project-level Gradle config
├── settings.gradle.kts             # Gradle settings
└── gradle.properties               # Gradle properties
```

## Development Workflow

### 1. Making Changes

```bash
# Create a feature branch
git checkout -b feature/my-new-feature

# Make changes to code
# ...

# Test your changes
./gradlew test

# Build debug APK
./gradlew assembleDebug
```

### 2. Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use Android Studio's auto-format: Ctrl+Alt+L / Cmd+Option+L
- Run code inspection: Analyze → Inspect Code

### 3. Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate test coverage report
./gradlew jacocoTestReport
```

## Next Steps

1. ✅ Set up Firebase
2. ✅ Configure backend API
3. 📖 Read [ARCHITECTURE.md](ARCHITECTURE.md) for detailed architecture
4. 📖 Read [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines
5. 🔨 Start developing features
6. 🧪 Write tests for your features
7. 📱 Test on multiple devices/emulators
8. 🚀 Deploy to Play Store (when ready)

## Useful Resources

### Documentation
- [Android Developer Guide](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Language](https://kotlinlang.org/)
- [Firebase Android](https://firebase.google.com/docs/android/setup)

### Tools
- [Android Studio](https://developer.android.com/studio)
- [Gradle](https://gradle.org/)
- [Git](https://git-scm.com/)

### Learning
- [Android Codelabs](https://developer.android.com/codelabs)
- [Kotlin Koans](https://play.kotlinlang.org/koans)
- [Compose Pathway](https://developer.android.com/courses/pathways/compose)

## Support

If you encounter any issues:

1. Check this SETUP guide
2. Review [README.md](README.md) for general information
3. Search existing [GitHub Issues](../../issues)
4. Create a new issue with:
   - Android Studio version
   - Device/Emulator info
   - Error logs
   - Steps to reproduce

## Tips for Success

✨ **Pro Tips:**
- Use Android Studio's built-in profiler for performance testing
- Enable "Don't keep activities" in Developer Options for testing lifecycle
- Use Layout Inspector to debug UI issues
- Keep dependencies up to date (but test thoroughly)
- Write tests as you develop (TDD approach)
- Use Git branches for features
- Commit often with meaningful messages

---

**Happy Coding! 🎉**

If you need help, don't hesitate to ask in the project discussions or issues.
