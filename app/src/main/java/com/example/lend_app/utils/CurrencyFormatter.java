package com.example.lend_app.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

  public static String formatToBrl(BigDecimal value) {
    NumberFormat brlFormat = DecimalFormat
      .getCurrencyInstance(new Locale("pt","BR"));

    return brlFormat.format(value);
  }
}
