package com.whitbread.venuesearch.model;

import java.io.Serializable;


public class RecommendedVenues implements Serializable {

    private Response response;

    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
}
