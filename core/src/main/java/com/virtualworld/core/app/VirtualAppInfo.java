package com.virtualworld.core.app;

public class VirtualAppInfo {

    private final String packageName;
    private final String displayName;
    private final String apkPath;

    public VirtualAppInfo(String packageName, String displayName, String apkPath) {
        this.packageName = packageName;
        this.displayName = displayName;
        this.apkPath = apkPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getApkPath() {
        return apkPath;
    }
}
