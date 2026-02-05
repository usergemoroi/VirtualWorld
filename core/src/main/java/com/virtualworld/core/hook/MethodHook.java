package com.virtualworld.core.hook;

public interface MethodHook {

    void beforeCall(String methodName, Object[] args);

    void afterCall(String methodName, Object result);
}
