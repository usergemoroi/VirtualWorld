package com.virtualworld.core.env;

import android.content.Context;

import java.io.File;

public class VEnvironment {

    private File virtualRoot;

    public void initialize(Context context) {
        virtualRoot = new File(context.getFilesDir(), "virtual_root");
        if (!virtualRoot.exists()) {
            virtualRoot.mkdirs();
        }
    }

    public File getVirtualRoot() {
        return virtualRoot;
    }

    public File getAppDir(String packageName) {
        File appDir = new File(virtualRoot, packageName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir;
    }

    public File getAppDataDir(String packageName) {
        File dataDir = new File(getAppDir(packageName), "data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        return dataDir;
    }
}
