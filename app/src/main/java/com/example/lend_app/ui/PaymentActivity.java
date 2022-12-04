package com.example.lend_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lend_app.R;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Meal;
import com.example.lend_app.utils.CurrencyFormatter;

public class PaymentActivity extends AppCompatActivity implements ExtraIntentKeys {

  private final String TITLE = "Pagamento";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);

    configureView();
  }

  private void configureView() {
    Intent intent = getIntent();
    Meal meal = (Meal) intent.getSerializableExtra(MEAL_KEY);
    TextView price = findViewById(R.id.payment_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));

    setTitle(TITLE);

    Button button = findViewById(R.id.payment_confirm_button);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(PaymentActivity.this, PaymentFinishedActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }
}