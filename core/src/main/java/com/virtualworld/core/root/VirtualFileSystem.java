package com.virtualworld.core.root;

import com.virtualworld.core.env.VEnvironment;

import java.io.File;

public class VirtualFileSystem {

    private final VEnvironment environment;
    private boolean rootEnabled;

    public VirtualFileSystem(VEnvironment environment) {
        this.environment = environment;
        this.rootEnabled = false;
    }

    public File getVirtualRoot() {
        return environment.getVirtualRoot();
    }

    public boolean isRootEnabled() {
        return rootEnabled;
    }

    public void setRootEnabled(boolean enabled) {
        this.rootEnabled = enabled;
    }

    public int getVirtualUid() {
        return rootEnabled ? 0 : 2000;
    }

    public String getVirtualSuPath() {
        return new File(getVirtualRoot(), "system/bin/su").getAbsolutePath();
    }
}
