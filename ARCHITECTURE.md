# VirtualWorld Architecture

## Overview

VirtualWorld is organized as a modular Android project with a lightweight UI module and a virtualization core module. The goal is to provide a virtual runtime for APK cloning, process isolation, and BlackBox-style controls.

## Modules

### 1. app

- `MainActivity`: Displays installed virtual apps and navigation to add apps/settings.
- `AddAppActivity`: Accepts an APK path to register in the virtual environment.
- `SettingsActivity`: Toggles virtual root access.
- `AppListAdapter`: RecyclerView adapter for virtual app listings.

### 2. core

- **VirtualCore**: Singleton entry point for initialization and access to managers.
- **VEnvironment**: Manages virtual root and app-specific directories.
- **VAppManager**: Handles virtual app installation and metadata storage.
- **VProcessManager**: Tracks virtual processes by package name and PID.
- **VPackageManager**: Lightweight package lookup over the virtual registry.
- **VActivityManagerService**: Starts/stops virtual app processes.
- **HookFramework / MethodHook**: Dispatches pre/post callbacks for hooked APIs.
- **VirtualFileSystem**: Root emulation and virtual UID handling.
- **PermissionController**: BlackBox permissions control.
- **Native Bridge**: JNI bridge for hook and IO redirection stubs.

## Native Layer

The native layer (C++ via CMake) includes placeholder implementations for:

- Hook engine stubs (`hook-engine.cpp`)
- IO redirection hooks (`io-redirect.cpp`)
- JNI bridge (`native-bridge.cpp`)

These provide the foundation for deeper syscall interception or binary instrumentation if extended.

## IPC

The design anticipates Binder-based IPC between the UI and core service components. Current stubs keep the API surface ready for expansion.

## Security & Sandbox

- Virtual file system root emulation can be toggled in settings.
- PermissionController centralizes permission decisions for sandboxed apps.
- Network and device info spoofing are prepared for BlackBox scenarios.
