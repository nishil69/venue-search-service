package com.whitbread.venuesearch.model;


import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    private int totalResults;
    private List<Group> groups;

    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
