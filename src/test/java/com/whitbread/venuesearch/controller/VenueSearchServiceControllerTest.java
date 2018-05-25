package com.whitbread.venuesearch.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.service.VenueSearchService;


/**
 * Just for demonstration that it can be done if we "need to" unit test controller.
 * However, testing controller does not make sense if dependent beans are unit tested, and a
 * separate "integration test" exists for the controller. We avoid double testing!
 */
@RunWith(MockitoJUnitRunner.class)
public class VenueSearchServiceControllerTest {

    private final static String LOCATION = "Mayfair";

    // class under test.
    @InjectMocks
    VenueSearchServiceController venueSearchServiceController;

    // collaborator(s)
    @Mock
    VenueSearchService venueSearchService;

    @Test
    public void testGetRecommendedVenues() throws Exception {
        // expectations
        List<Venue> expected = Mockito.mock(List.class);
        Mockito.when(venueSearchService.getRecommendedVenues(LOCATION)).thenReturn(expected);

        ResponseEntity<List<Venue>> actual = venueSearchServiceController.getRecommendedVenues(LOCATION);

        Mockito.verify(venueSearchService).getRecommendedVenues(LOCATION);
        assertThat(actual.getBody(), is(expected));
    }
}
