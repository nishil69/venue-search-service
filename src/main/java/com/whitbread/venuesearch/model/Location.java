package com.whitbread.venuesearch.model;

public class Location {

  private String address;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private String name;
  private String cc;
  private Double lat;
  private Double lng;

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public String getName() {
    return name;
  }

  public String getCc() {
    return cc;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }
}
