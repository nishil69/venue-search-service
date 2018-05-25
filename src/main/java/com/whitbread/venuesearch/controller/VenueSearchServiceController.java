package com.whitbread.venuesearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.service.VenueSearchService;

@RestController
@RequestMapping("/api")
public class VenueSearchServiceController {

    @Autowired
    private VenueSearchService venueSearchService;

    /**
     * Performs a search for recommended near the specified location using Foursquare Venues API.
     */
    @GetMapping("/venues")
    public ResponseEntity<List<Venue>> getRecommendedVenues(@RequestParam(value = "name"/*, required=false*/) String name) throws Exception {
        System.out.println("Received GET request for recommended venues near: " + name);

        final List<Venue> venues = venueSearchService.getRecommendedVenues(name);

        return ResponseEntity.ok().body(venues);
    }
}
