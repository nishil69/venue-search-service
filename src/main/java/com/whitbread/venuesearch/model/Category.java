package com.whitbread.venuesearch.model;

public class Category {

  private String id;
  private String name;
  private String pluralName;
  private String shortName;
  private Icon icon;
  private Boolean primary;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPluralName() {
    return pluralName;
  }

  public String getShortName() {
    return shortName;
  }

  public Icon getIcon() {
    return icon;
  }

  public Boolean getPrimary() {
    return primary;
  }
}
