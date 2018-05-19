package com.whitbread.venuesearch.model;


import java.io.Serializable;

public class Stats implements Serializable {

  private int checkinsCount;
  private int usersCount;
  private int tipCount;

  public int getCheckinsCount() {
    return checkinsCount;
  }
  public int getUsersCount() {
    return usersCount;
  }
  public int getTipCount() { return tipCount; }
}
