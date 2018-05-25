package com.whitbread.venuesearch.model;

import java.util.List;

/**
 * Venue class to hold all the venue specific details. For this task, we may not need all the info,
 * but Locstion is probably the one we want as it contains address, latitude and longitude etc..
 */
public class Venue {

  private String id;
  private String name;
  private Contact contact;
  private Boolean verified;
  private Stats stats;
  private String url;
  private String rating;

  private Location location;
  private List<Category> categories;

  public String getId() { return id;}
  public void setId(String id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Contact getContact() { return contact; }
  public void setContact(Contact contact) { this.contact = contact; }

  public Boolean getVerified() { return verified; }
  public void setVerified(Boolean verified) { this.verified = verified; }

  public Stats getStats() { return stats; }
  public void setStats(Stats stats) { this.stats = stats; }

  public String getUrl() { return url; }
  public void setUrl(String url) { this.url = url; }

  public String getRating() { return rating;}
  public void setRating(String rating) { this.rating = rating;  }

  public Location getLocation() { return location; }
  public void setLocation(Location location) { this.location = location; }

  public List<Category> getCategories() { return categories; }
  public void setCategories(List<Category> categories) { this.categories = categories;}
}
