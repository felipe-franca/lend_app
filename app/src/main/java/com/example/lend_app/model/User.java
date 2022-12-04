package com.example.lend_app.model;

import java.io.Serializable;

public class User implements Serializable {
  private final String name;
  private final String email;
  private final String password;

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
