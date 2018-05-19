package com.whitbread.venuesearch.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whitbread.venuesearch.config.AppConfig;
import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.model.Item;
import com.whitbread.venuesearch.model.RecommendedVenues;

@Service
public class VenueSearchServiceImpl implements VenueSearchService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Venue> getRecommendedVenues(String location) {
        final String clientId = appConfig.getClientId();
        final String clientSecret = appConfig.getClientSecret();
        final String version = appConfig.getVersion();
        final String baseURI = appConfig.getVenuesExploreUri();
        final String fullURI = baseURI + "?client_id={clientId}&client_secret={clientSecret}&v={version}&near={location}";

        System.out.println("Making GET request: " + fullURI);

        final RecommendedVenues recommendedVenues = restTemplate.getForObject(fullURI, RecommendedVenues.class, clientId, clientSecret, version, location);
        final List<Item> items = recommendedVenues.getResponse().getGroups().get(0).getItems();
        final List<Venue> venues = recommendedVenues.getResponse().getGroups().get(0).getItems().stream().map(Item::getVenue).collect(toList());

        System.out.println("Received " + venues.size() + " recommended places.");

        return venues;
    }
}
