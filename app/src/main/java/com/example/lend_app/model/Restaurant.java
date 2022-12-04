package com.example.lend_app.model;

import java.io.Serializable;

public class Restaurant implements Serializable {
  private final int id;
  private final String title;
  private final String description;
  private final String image;

  public Restaurant(int id, String title, String description, String image) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.image = image;
  }

  public int getId() { return this.id; }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public String getImage() {
    return this.image;
  }
}
