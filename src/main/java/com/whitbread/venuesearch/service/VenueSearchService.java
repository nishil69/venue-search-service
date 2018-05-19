package com.whitbread.venuesearch.service;

import java.util.List;

import com.whitbread.venuesearch.model.Venue;

public interface VenueSearchService {
    List<Venue> getRecommendedVenues(String location);
}
