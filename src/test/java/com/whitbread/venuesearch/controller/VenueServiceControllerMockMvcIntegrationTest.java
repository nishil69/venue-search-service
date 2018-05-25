package com.whitbread.venuesearch.controller;

import static java.util.stream.Collectors.toList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.whitbread.venuesearch.Util.createDummyRecommendedValues;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import com.whitbread.venuesearch.bootstrap.VenueSearchServiceApplication;
import com.whitbread.venuesearch.model.Item;
import com.whitbread.venuesearch.model.RecommendedVenues;
import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.service.VenueSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK, classes = VenueSearchServiceApplication.class)
@AutoConfigureMockMvc
public class VenueServiceControllerMockMvcIntegrationTest {

    @MockBean
    private VenueSearchService venueSearchService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRecommendedVenues() throws Exception {
        RecommendedVenues recommendedVenues = createDummyRecommendedValues();
        List<Venue> venues = recommendedVenues.getResponse().getGroups().get(0).getItems().stream().map(Item::getVenue).collect(toList());

        when(venueSearchService.getRecommendedVenues(anyString()))
                .thenReturn(venues);

        // showing alternative approach too - although this test is not that useful in my view!
      /*  MockHttpServletResponse response = mvc.perform(
                get("/api/venues/?name=soho")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));*/

        this.mvc.perform(get("/api/venues?name=Mayfair"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id", Matchers.is("5028d6e5e88907e1c5a3717b")))
                .andExpect(jsonPath("$[0].name", Matchers.is("Phillips")));


        verify(this.venueSearchService, times(1)).getRecommendedVenues(anyString());
    }

    @Test
    public void testHttpClientErrorException() throws Exception {

        when(venueSearchService.getRecommendedVenues(anyString()))
                            .thenThrow(HttpClientErrorException.class);

        MockHttpServletResponse response = mvc.perform(
                get("/api/venues?name=BAD_LOCATION").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is((HttpStatus.BAD_REQUEST).value()));
        verify(this.venueSearchService, times(1)).getRecommendedVenues(anyString());
    }
}
