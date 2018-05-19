package com.whitbread.venuesearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "/venues" , method = RequestMethod.GET, params = "name")
    @ResponseStatus(value=HttpStatus.OK)
    public List<Venue> getRecommededVenues(@RequestParam("name") String name) {
        System.out.println("Received GET request for recommended venues near: " + name);

        final List<Venue> venues = venueSearchService.getRecommendedVenues(name);

        return venues;
    }
}
