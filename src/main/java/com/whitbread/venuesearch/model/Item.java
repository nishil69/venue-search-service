package com.whitbread.venuesearch.model;


import java.io.Serializable;

public class Item implements Serializable {

    private Venue venue;

    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
