package com.virtualworld.core.blackbox;

public class NetworkInterceptor {

    private boolean blocked;

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
