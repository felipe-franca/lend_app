package com.example.lend_app.model;

public class ReservationResume implements ReservatioInterface {
  private final String restaurantName;
  private final String reservationStatus;
  private final String reservationDate;
  private final String mealTitle;
  private final String mealImage;

  public ReservationResume(String restaurantName, String reservationStatus, String reservationDate, String mealTitle, String mealImage) {
    this.restaurantName = restaurantName;
    this.reservationStatus = reservationStatus;
    this.reservationDate = reservationDate;
    this.mealTitle = mealTitle;
    this.mealImage = mealImage;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public String getReservationStatus() {
    return reservationStatus;
  }

  public String getReservationDate() {
    return reservationDate;
  }

  public String getMealTitle() { return mealTitle; }

  public String getMealImage() { return mealImage; }
}
