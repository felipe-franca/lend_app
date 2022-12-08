package com.example.lend_app.model;

public class Payment {
  private final int id;
  private final String cardNumber;
  private final String expiresIn;
  private final String cvc;
  private final String owner;

  public Payment(String cardNumber, String expiresIn, String cvc, String owner) {
    this.id = 0;
    this.cardNumber = cardNumber;
    this.expiresIn = expiresIn;
    this.cvc = cvc;
    this.owner = owner;
  }

  public int getId() {
    return this.id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getExpiresIn() {
    return expiresIn;
  }

  public String getCvc() {
    return cvc;
  }

  public String getOwner() {
    return owner;
  }
}
