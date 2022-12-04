package com.example.lend_app.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Meal implements Serializable {
  private final int id;
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final String image;

  public Meal(int id, String name, String description, BigDecimal price, String image) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
  }

  public int getId() { return id; }

  public String getName() { return name; }

  public String getDescription() { return description; }

  public BigDecimal getPrice() { return price; }

  public String getImage() { return image; }
}
