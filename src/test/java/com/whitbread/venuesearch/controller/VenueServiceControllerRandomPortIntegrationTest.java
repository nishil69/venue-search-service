package com.whitbread.venuesearch.controller;

import static java.util.stream.Collectors.toList;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import static com.whitbread.venuesearch.Util.createDummyRecommendedValues;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.whitbread.venuesearch.bootstrap.VenueSearchServiceApplication;
import com.whitbread.venuesearch.exception.ExceptionResponse;
import com.whitbread.venuesearch.model.Item;
import com.whitbread.venuesearch.model.RecommendedVenues;
import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.service.VenueSearchService;

/**
 * Useful "integration test" for controller as it uses real tomcat. random port and testRestTemplate.
 * Spring "TestRestTemplate" can help us test basic authentication etc.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = VenueSearchServiceApplication.class)
public class VenueServiceControllerRandomPortIntegrationTest {

    @MockBean
    private VenueSearchService venueSearchService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRecommendedVenues() throws Exception {
        RecommendedVenues recommendedVenues = createDummyRecommendedValues();
        List<Venue> venues = recommendedVenues.getResponse().getGroups().get(0).getItems().stream().map(Item::getVenue).collect(toList());

        when(venueSearchService.getRecommendedVenues(anyString()))
                .thenReturn(venues);

        ResponseEntity<List<Venue>> response = restTemplate.exchange("/api/venues?name=mayfair",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Venue>>() {
                });

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(instanceOf(List.class)));

        verify(this.venueSearchService, times(1)).getRecommendedVenues(anyString());
    }

    @Test
    public void testHttpClientErrorException() throws Exception {

        when(venueSearchService.getRecommendedVenues(anyString()))
                .thenThrow(HttpClientErrorException.class);

        ResponseEntity<ExceptionResponse> response = restTemplate.getForEntity("/api/venues?name=BAD_LOCATION", ExceptionResponse.class);

        assertThat(response.getStatusCode(), Is.is((HttpStatus.BAD_REQUEST)));
        verify(this.venueSearchService, times(1)).getRecommendedVenues(anyString());
    }
}
