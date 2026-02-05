package com.virtualworld.core.server;

import com.virtualworld.core.app.VAppManager;
import com.virtualworld.core.app.VirtualAppInfo;

import java.util.List;

public class VPackageManagerService {

    private final VAppManager appManager;

    public VPackageManagerService(VAppManager appManager) {
        this.appManager = appManager;
    }

    public List<VirtualAppInfo> getInstalledApps() {
        return appManager.getInstalledApps();
    }
}
