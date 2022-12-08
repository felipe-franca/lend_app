package com.example.lend_app.model;

public class Reservation {
  private final int id;
  private final Restaurant restaurant;
  private final Meal meal;
  private final AvailableTimes availableTimes;

  public Reservation(int id, Restaurant restaurant, Meal meal, AvailableTimes availableTimes) {
    this.id = id;
    this.restaurant = restaurant;
    this.meal = meal;
    this.availableTimes = availableTimes;
  }

  public int getId() {
    return id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public Meal getMeal() {
    return meal;
  }

  public AvailableTimes getAvailableTimes() {
    return availableTimes;
  }
}
