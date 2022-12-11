package com.example.lend_app.model;

public class Payment {
  private final int id;
  private final String cardNumber;
  private final String expiresIn;
  private final String cvc;
  private final String owner;
  private final String ownerEmail;

  public Payment(String cardNumber, String expiresIn, String cvc, String owner, String ownerEmail) {
    this.id = 0;
    this.cardNumber = cardNumber;
    this.expiresIn = expiresIn;
    this.cvc = cvc;
    this.owner = owner;
    this.ownerEmail = ownerEmail;
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

  public String getOwnerEmail() { return ownerEmail; }
}
