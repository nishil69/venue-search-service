package com.whitbread.venuesearch.controller;

import static java.util.stream.Collectors.toList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.whitbread.venuesearch.Util.createDummyRecommendedValues;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.whitbread.venuesearch.bootstrap.VenueSearchServiceApplication;
import com.whitbread.venuesearch.model.Item;
import com.whitbread.venuesearch.model.RecommendedVenues;
import com.whitbread.venuesearch.model.Venue;
import com.whitbread.venuesearch.service.VenueSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK, classes = VenueSearchServiceApplication.class)
@AutoConfigureMockMvc
public class VenueServiceControllerIntegrationTest {

    @MockBean
    private VenueSearchService venueSearchService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRecommendedVenues() throws Exception {
        RecommendedVenues recommendedVenues = createDummyRecommendedValues();
        List<Venue> venues = recommendedVenues.getResponse().getGroups().get(0).getItems().stream().map(Item::getVenue).collect(toList());

        when(venueSearchService.getRecommendedVenues(anyString())).thenReturn(venues);

        this.mvc.perform(get("/api/venues?name=Mayfair"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id", is("5028d6e5e88907e1c5a3717b")))
                .andExpect(jsonPath("$[0].name", is("Phillips")));

        verify(this.venueSearchService, times(1)).getRecommendedVenues(anyString());
    }
}
