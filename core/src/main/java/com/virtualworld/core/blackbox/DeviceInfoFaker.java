package com.virtualworld.core.blackbox;

public class DeviceInfoFaker {

    private String fakeDeviceId = "VW-DEVICE";
    private String fakeImei = "000000000000000";

    public void setFakeDeviceId(String fakeDeviceId) {
        this.fakeDeviceId = fakeDeviceId;
    }

    public void setFakeImei(String fakeImei) {
        this.fakeImei = fakeImei;
    }

    public String getFakeDeviceId() {
        return fakeDeviceId;
    }

    public String getFakeImei() {
        return fakeImei;
    }
}
