package com.virtualworld.core.server;

import java.util.HashMap;
import java.util.Map;

public class VAccountManagerService {

    private final Map<String, String> accounts = new HashMap<>();

    public void addAccount(String name, String token) {
        accounts.put(name, token);
    }

    public String getToken(String name) {
        return accounts.get(name);
    }
}
