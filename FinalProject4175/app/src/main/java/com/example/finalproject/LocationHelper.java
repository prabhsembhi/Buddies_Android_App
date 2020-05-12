package com.example.finalproject;

public class LocationHelper {

    private double Longitude;
    private double Latitude;
    private String name;

    public LocationHelper(String name, double longitude, double latitude) {
        this.name = name;
        Longitude = longitude;
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
