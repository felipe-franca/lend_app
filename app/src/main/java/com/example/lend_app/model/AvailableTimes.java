package com.example.lend_app.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class AvailableTimes implements Serializable {
  private final String hour;
  private final int table;

  public AvailableTimes(String hour, int table) {
    this.hour = hour;
    this.table = table;
  }

  public String getHour() { return hour; }

  public int getTable() { return table; }

  public String toString() {
    return "Horário: " + this.getHour() + ", Mesa: " + this.getTable();
  }
}
