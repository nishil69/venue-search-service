package com.whitbread.venuesearch.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.net.URL;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.whitbread.venuesearch.bootstrap.VenueSearchServiceApplication;
import com.whitbread.venuesearch.exception.ExceptionResponse;
import com.whitbread.venuesearch.model.Venue;

/**
 * Example of a real "outside server" integration test" as it uses real tomcat.
 * We use random port and springframework's testRestTemplate to make real request via 4sq API.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = VenueSearchServiceApplication.class)
public class VenueServiceControllerRandomPortIntegrationTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        this.baseURL = new URL("http://localhost:" + port);
    }

    @Test
    public void testRecommendedVenues() throws Exception {

        ResponseEntity<List<Venue>> response = restTemplate.exchange(baseURL.toString() +  "/api/venues?name=mayfair",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Venue>>() {
                });

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(30));
    }

    @Test
    public void testHttpClientErrorException() throws Exception {

        ResponseEntity<ExceptionResponse> response =
                restTemplate.getForEntity(baseURL.toString() + "/api/venues?name=BAD_LOCATION",
                ExceptionResponse.class);

        assertThat(response.getStatusCode(), Is.is((HttpStatus.BAD_REQUEST)));
        assertThat(response.getBody(), is(ExceptionResponse.class));

    }
}
