package com.whitbread.venuesearch.service;

import static java.util.stream.Collectors.toList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static com.whitbread.venuesearch.Util.createDummyRecommendedValues;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.whitbread.venuesearch.config.AppConfig;
import com.whitbread.venuesearch.model.Item;
import com.whitbread.venuesearch.model.RecommendedVenues;
import com.whitbread.venuesearch.model.Venue;

@RunWith(MockitoJUnitRunner.class)
public class VenueSearchServiceImplTest {

    private final static String LOCATION = "Mayfair";

    // class under test
    @InjectMocks
    private VenueSearchServiceImpl venueSearchService;

    // collaborator(s)
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private AppConfig appConfig;

    @Test
    public void testGetRecommendedVenues() throws Exception {

        final String clientId = "dummyClientId";
        final String clientSecret = "dummyClientSecret";
        final String version = "dummyVersion";
        final String baseURI = "dummyBaseURI";

        Mockito.when(appConfig.getClientId()).thenReturn(clientId);
        Mockito.when(appConfig.getClientSecret()).thenReturn(clientSecret);
        Mockito.when(appConfig.getVersion()).thenReturn(version);
        Mockito.when(appConfig.getVenuesExploreUri()).thenReturn(baseURI);

        String fullURI = baseURI + "?client_id={clientId}&client_secret={clientSecret}&v={version}&near={location}";

        // create fake/stub objects etc.
        final RecommendedVenues recommendedVenues = createDummyRecommendedValues();
        final List<Venue> expected = recommendedVenues.getResponse().getGroups().get(0).getItems().stream().map(Item::getVenue).collect(toList());

        Mockito.when(restTemplate.getForObject(fullURI, RecommendedVenues.class,
                clientId,
                clientSecret,
                version,
                LOCATION)).thenReturn(recommendedVenues);

        // make the call
        List<Venue> actual = venueSearchService.getRecommendedVenues(LOCATION);

        // verify that the actual method call took place.
        Mockito.verify(restTemplate).getForObject(fullURI, RecommendedVenues.class, clientId, clientSecret, version, LOCATION);
        assertThat(actual, is(expected));
        assertEquals(actual.get(0).getId(), expected.get(0).getId());   // demo
    }
}
