package com.virtualworld.core.server;

import android.os.Binder;

public class VServiceBinder extends Binder {

    private final Object service;

    public VServiceBinder(Object service) {
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService() {
        return (T) service;
    }
}
