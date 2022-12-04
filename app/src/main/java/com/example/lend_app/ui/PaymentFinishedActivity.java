package com.example.lend_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lend_app.R;

public class PaymentFinishedActivity extends AppCompatActivity {

  private final String TITLE = "Resumo do Pagamento";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_finished);

    configView();
  }

  private void configView() {
    setTitle(TITLE);
  }
}