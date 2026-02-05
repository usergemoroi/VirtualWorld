package com.virtualworld.core.server;

import com.virtualworld.core.process.VProcessManager;

public class VActivityManagerService {

    private final VProcessManager processManager;

    public VActivityManagerService(VProcessManager processManager) {
        this.processManager = processManager;
    }

    public int launchVirtualApp(String packageName) {
        return processManager.startProcess(packageName);
    }

    public void stopVirtualApp(String packageName) {
        processManager.stopProcess(packageName);
    }
}
