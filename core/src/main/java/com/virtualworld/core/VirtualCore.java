package com.virtualworld.core;

import android.content.Context;

import com.virtualworld.core.app.VAppManager;
import com.virtualworld.core.env.VEnvironment;
import com.virtualworld.core.hook.HookFramework;
import com.virtualworld.core.pm.VPackageManager;
import com.virtualworld.core.process.VProcessManager;
import com.virtualworld.core.root.VirtualFileSystem;
import com.virtualworld.core.server.VActivityManagerService;
import com.virtualworld.core.blackbox.PermissionController;

public final class VirtualCore {

    private static VirtualCore instance;

    private final Context context;
    private final VEnvironment environment;
    private final VAppManager appManager;
    private final VPackageManager packageManager;
    private final VProcessManager processManager;
    private final VActivityManagerService activityManagerService;
    private final HookFramework hookFramework;
    private final VirtualFileSystem fileSystem;
    private final PermissionController permissionController;

    private VirtualCore(Context context) {
        this.context = context.getApplicationContext();
        this.environment = new VEnvironment();
        this.environment.initialize(this.context);
        this.appManager = new VAppManager(this.environment);
        this.packageManager = new VPackageManager(this.appManager);
        this.processManager = new VProcessManager();
        this.activityManagerService = new VActivityManagerService(this.processManager);
        this.hookFramework = new HookFramework();
        this.fileSystem = new VirtualFileSystem(this.environment);
        this.permissionController = new PermissionController();
        NativeBridge.initialize();
    }

    public static synchronized void init(Context context) {
        if (instance == null) {
            instance = new VirtualCore(context);
        }
    }

    public static VirtualCore get() {
        if (instance == null) {
            throw new IllegalStateException("VirtualCore is not initialized");
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public VEnvironment getEnvironment() {
        return environment;
    }

    public VAppManager getAppManager() {
        return appManager;
    }

    public VPackageManager getPackageManager() {
        return packageManager;
    }

    public VProcessManager getProcessManager() {
        return processManager;
    }

    public VActivityManagerService getActivityManagerService() {
        return activityManagerService;
    }

    public HookFramework getHookFramework() {
        return hookFramework;
    }

    public VirtualFileSystem getFileSystem() {
        return fileSystem;
    }

    public PermissionController getPermissionController() {
        return permissionController;
    }
}
