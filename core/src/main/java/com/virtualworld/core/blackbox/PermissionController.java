package com.virtualworld.core.blackbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PermissionController {

    private final Map<String, Boolean> permissionStates = new HashMap<>();

    public void setPermission(String permission, boolean granted) {
        permissionStates.put(permission, granted);
    }

    public boolean isPermissionGranted(String permission) {
        Boolean granted = permissionStates.get(permission);
        return granted != null && granted;
    }

    public Map<String, Boolean> getPermissionSnapshot() {
        return Collections.unmodifiableMap(new HashMap<>(permissionStates));
    }
}
