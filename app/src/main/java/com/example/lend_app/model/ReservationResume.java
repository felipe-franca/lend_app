package com.example.lend_app.model;

public class ReservationResume implements ReservatioInterface {
  private final String restaurantName;
  private final String reservationStatus;
  private final String reservationDate;

  public ReservationResume(String restaurantName, String reservationStatus, String reservationDate) {
    this.restaurantName = restaurantName;
    this.reservationStatus = reservationStatus;
    this.reservationDate = reservationDate;
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
}
