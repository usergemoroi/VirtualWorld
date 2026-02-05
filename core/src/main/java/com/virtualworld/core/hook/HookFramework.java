package com.virtualworld.core.hook;

import com.virtualworld.core.NativeBridge;

import java.util.ArrayList;
import java.util.List;

public class HookFramework {

    private final List<MethodHook> hooks = new ArrayList<>();

    public void registerHook(MethodHook hook) {
        hooks.add(hook);
        try {
            NativeBridge.notifyHookInstalled(hook.getClass().getSimpleName());
        } catch (UnsatisfiedLinkError ignored) {
        }
    }

    public void dispatchBefore(String methodName, Object[] args) {
        for (MethodHook hook : hooks) {
            hook.beforeCall(methodName, args);
        }
    }

    public void dispatchAfter(String methodName, Object result) {
        for (MethodHook hook : hooks) {
            hook.afterCall(methodName, result);
        }
    }
}
