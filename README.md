# VirtualWorld

VirtualWorld is an Android virtual application engine designed for research and education. It provides a virtual runtime for cloning APKs, managing sandboxed processes, and applying BlackBox-style permission control in a contained environment.

## Highlights

- Virtual application manager with APK ingestion flow
- Hook framework to register method interceptors
- Virtual file system with root emulation toggle
- BlackBox controls for permissions, network, device info, and location spoofing
- Modular architecture with separate `app` and `core` modules

## Modules

- **app**: User interface to list and install virtual apps, plus settings for root access.
- **core**: Virtualization kernel with app/process managers, hooks, root environment, and native bridge.

## Requirements

- Android Studio (Hedgehog or newer)
- JDK 17
- Android SDK 21+ (target SDK 33)

## Build

```bash
./gradlew assembleDebug
```

The APK will be generated in `app/build/outputs/apk/debug/` (e.g. `app-debug.apk`). Rename it to `VirtualWorld.apk` if required for distribution.

## Notes

This project is intended for controlled environments. Some third-party APKs may not function due to virtualization limitations or security constraints.
