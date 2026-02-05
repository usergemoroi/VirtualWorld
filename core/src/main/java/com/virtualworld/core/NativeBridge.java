package com.virtualworld.core;

public final class NativeBridge {

    private static boolean loaded;

    private NativeBridge() {
    }

    public static synchronized void initialize() {
        if (!loaded) {
            try {
                System.loadLibrary("virtualworld");
                loaded = true;
            } catch (UnsatisfiedLinkError ignored) {
                loaded = false;
            }
        }
    }

    public static native void notifyHookInstalled(String hookName);
}
