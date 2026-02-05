package com.virtualworld.core.blackbox;

public class LocationSpoofer {

    private double latitude;
    private double longitude;

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
