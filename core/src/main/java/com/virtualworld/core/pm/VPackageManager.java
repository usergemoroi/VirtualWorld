package com.virtualworld.core.pm;

import com.virtualworld.core.app.VAppManager;
import com.virtualworld.core.app.VirtualAppInfo;

import java.util.List;

public class VPackageManager {

    private final VAppManager appManager;

    public VPackageManager(VAppManager appManager) {
        this.appManager = appManager;
    }

    public VirtualAppInfo getPackageInfo(String packageName) {
        List<VirtualAppInfo> apps = appManager.getInstalledApps();
        for (VirtualAppInfo info : apps) {
            if (info.getPackageName().equals(packageName)) {
                return info;
            }
        }
        return null;
    }
}
