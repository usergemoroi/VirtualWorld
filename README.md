# VirtualWorld - Android Application

VirtualWorld is a social virtual reality platform for Android that allows users to explore immersive 3D worlds, interact with friends, customize avatars, and participate in a virtual economy.

## Features

### Core Features
- **Virtual World Navigation**: Explore various 3D virtual worlds
- **User Authentication**: Email/password and social media login (Google, Facebook)
- **Profile Management**: Customizable user profiles with avatars
- **Social Features**: 
  - Friends system with friend requests
  - Real-time messaging and group chat
  - Online status indicators
- **Avatar Customization**: Personalize your virtual avatar
- **Virtual Economy**: In-app purchases and virtual currency
- **Real-time Multiplayer**: Interact with other users in real-time
- **Push Notifications**: Stay updated with friend requests, messages, and events

## Technology Stack

### Architecture
- **MVVM** (Model-View-ViewModel) architecture pattern
- **Clean Architecture** with separation of concerns
- **Repository Pattern** for data management

### Libraries & Frameworks
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Material 3** - Material Design components
- **Hilt** - Dependency injection
- **Room** - Local database
- **Retrofit** - REST API client
- **OkHttp** - HTTP client
- **Coroutines & Flow** - Asynchronous programming
- **DataStore** - Preferences storage
- **Coil** - Image loading
- **Firebase** - Authentication, Cloud Messaging, Analytics
- **Navigation Component** - In-app navigation
- **SceneForm** - 3D rendering (for virtual worlds)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/virtualworld/app/
│   │   ├── data/
│   │   │   ├── api/           # API interfaces
│   │   │   ├── database/      # Room database and DAOs
│   │   │   ├── local/         # Local data sources
│   │   │   ├── model/         # Data models
│   │   │   ├── repository/    # Repository implementations
│   │   │   └── service/       # Background services
│   │   ├── di/                # Dependency injection modules
│   │   ├── presentation/      # UI layer
│   │   │   ├── components/    # Reusable UI components
│   │   │   ├── navigation/    # Navigation setup
│   │   │   ├── screens/       # Feature screens
│   │   │   └── theme/         # App theme and styling
│   │   ├── util/              # Utility classes
│   │   └── VirtualWorldApplication.kt
│   ├── res/                   # Resources
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 or newer
- Android SDK 26 (Android 8.0) or higher
- Gradle 8.2 or newer

### Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/virtualworld.git
cd virtualworld
```

2. Open the project in Android Studio

3. Configure Firebase:
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Add your Android app to the Firebase project
   - Download `google-services.json` and place it in the `app/` directory
   - Enable Authentication (Email/Password, Google, Facebook)
   - Enable Cloud Firestore
   - Enable Cloud Messaging

4. Configure API endpoint:
   - Open `app/src/main/java/com/virtualworld/app/di/AppModule.kt`
   - Update the base URL in `provideRetrofit()` to your backend API

5. Sync Gradle and build the project

6. Run the application on an emulator or physical device

## Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## API Integration

The app connects to a RESTful API backend. The expected endpoints are:

- `POST /auth/login` - User login
- `POST /auth/register` - User registration
- `GET /users/{userId}` - Get user profile
- `GET /worlds` - Get virtual worlds
- `GET /friends/{userId}` - Get user friends
- `GET /messages/{userId}` - Get user messages
- And more...

See `VirtualWorldApi.kt` for the complete API specification.

## Features Implementation Status

Based on the [Blackbox Exploration Findings](BLACKBOX_EXPLORATION_FINDINGS.md):

### ✅ Implemented
- User authentication (email/password)
- User profile management
- Friends system
- Messaging system
- Virtual worlds browsing
- Avatar system structure
- In-app purchase framework
- Push notifications setup
- Material Design UI

### 🚧 In Progress
- 3D world rendering
- Real-time multiplayer
- Avatar customization UI
- Biometric authentication
- Advanced chat features

### 📋 Planned
- Tutorial/onboarding flow
- Accessibility features
- Tablet optimization
- Performance optimizations
- Offline mode

## Performance Considerations

- Minimum RAM: 2GB (4GB recommended)
- Target devices: Android 8.0+ (API 26+)
- Optimized for devices with 4GB+ RAM
- Image caching with Coil
- Database queries optimized with Room
- Network calls optimized with OkHttp connection pooling

## Security

- HTTPS for all network communications
- Secure token storage with DataStore
- Password hashing on backend
- Session management
- Input validation
- ProGuard/R8 code obfuscation for release builds

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

Project Link: [https://github.com/yourusername/virtualworld](https://github.com/yourusername/virtualworld)

## Acknowledgments

- Material Design by Google
- Jetpack Compose team
- All open source contributors

---

**Version**: 1.0.0  
**Last Updated**: February 5, 2026  
**Status**: Development
