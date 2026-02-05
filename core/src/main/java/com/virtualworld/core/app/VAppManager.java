package com.virtualworld.core.app;

import com.virtualworld.core.env.VEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VAppManager {

    private final VEnvironment environment;
    private final List<VirtualAppInfo> installedApps = new ArrayList<>();

    public VAppManager(VEnvironment environment) {
        this.environment = environment;
    }

    public synchronized VirtualAppInfo installPackage(String apkPath) {
        String packageName = derivePackageName(apkPath);
        String displayName = packageName.substring(packageName.lastIndexOf('.') + 1);
        VirtualAppInfo info = new VirtualAppInfo(packageName, displayName, apkPath);
        installedApps.add(info);
        File appDir = environment.getAppDir(packageName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return info;
    }

    public synchronized List<VirtualAppInfo> getInstalledApps() {
        return Collections.unmodifiableList(new ArrayList<>(installedApps));
    }

    private String derivePackageName(String apkPath) {
        String sanitized = apkPath.replace("\\", "/");
        String name = sanitized.substring(sanitized.lastIndexOf('/') + 1);
        if (name.endsWith(".apk")) {
            name = name.substring(0, name.length() - 4);
        }
        return "virtual." + name.toLowerCase().replaceAll("[^a-z0-9_]", "_");
    }
}
