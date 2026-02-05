package com.virtualworld.core.process;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VProcessManager {

    private final Map<String, Integer> runningProcesses = new HashMap<>();
    private int nextPid = 1000;

    public synchronized int startProcess(String packageName) {
        int pid = nextPid++;
        runningProcesses.put(packageName, pid);
        return pid;
    }

    public synchronized void stopProcess(String packageName) {
        runningProcesses.remove(packageName);
    }

    public synchronized Map<String, Integer> getRunningProcesses() {
        return Collections.unmodifiableMap(new HashMap<>(runningProcesses));
    }
}
