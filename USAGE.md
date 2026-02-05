# VirtualWorld Usage

## Installing an APK into the Virtual Environment

1. Build and install the VirtualWorld application on a device/emulator.
2. Launch the app and tap **Add App**.
3. Enter the path to the APK stored on the device and tap **Install**.
4. The installed entry will appear in the main list of virtual apps.

## Managing Root Access

1. Open **Settings** from the main screen.
2. Toggle **Enable root** to emulate root within the sandbox.
3. Root state will be applied to virtual UID resolution in the core module.

## Notes

- APK execution is currently simulated; actual process injection/hooking is stubbed.
- BlackBox controls can be expanded via the `PermissionController`, `DeviceInfoFaker`, and `LocationSpoofer` classes.
