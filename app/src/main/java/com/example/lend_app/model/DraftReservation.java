package com.example.lend_app.model;

import java.util.ArrayList;

public class DraftReservation {
  private int id;
  private final int restaurantId;
  private final String userEmail;
  private final int mealId;
  private final ArrayList<Integer> availableTimesIdList;

  public DraftReservation(int restaurantId, String userEmail, int mealId, ArrayList<Integer> availableTimesIdList) {
    this.restaurantId = restaurantId;
    this.userEmail = userEmail;
    this.mealId = mealId;
    this.availableTimesIdList = availableTimesIdList;
    this.id = 0;
  }

  public int getId() {
    return this.id;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public int getMealId() {
    return mealId;
  }

  public ArrayList<Integer> getAvailableTimesIdList() {
    return availableTimesIdList;
  }

  public void setId(int id) {
    this.id = id;
  }
}
