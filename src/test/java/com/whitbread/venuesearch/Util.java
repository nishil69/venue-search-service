package com.whitbread.venuesearch;

import java.util.Collections;
import java.util.List;

import com.whitbread.venuesearch.model.*;

public final class Util {
    private Util() { }

    public static RecommendedVenues createDummyRecommendedValues() {
        final RecommendedVenues recommendedVenues = new RecommendedVenues();
        final Response response = new Response();
        recommendedVenues.setResponse(response);

        final Group group = new Group();
        final List<Group> groups = Collections.singletonList(group);
        response.setGroups(groups);

        final Item item = new Item();
        final List<Item> items = Collections.singletonList(item);
        group.setItems(items);

        final Venue venue = new Venue();
        venue.setId("5028d6e5e88907e1c5a3717b");
        venue.setName("Phillips");

        item.setVenue(venue);

        return recommendedVenues;
    }


}
