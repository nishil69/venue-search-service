package com.whitbread.venuesearch.model;

public class RequestWrapper {

    public String getLocation() {
        return location;
    }

    public RequestWrapper setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getLatitude() {
        return latitude;
    }

    public RequestWrapper setLatitude(int latitude) {
        this.latitude = latitude;
        return this;
    }

    private String location;
    private int latitude;

    public RequestWrapper setPopular(boolean popular) {
        this.popular = popular;
        return this;
    }

    private boolean popular;
}
