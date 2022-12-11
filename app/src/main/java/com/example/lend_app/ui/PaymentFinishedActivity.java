package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Meal;
import com.example.lend_app.utils.CurrencyFormatter;
import com.example.lend_app.utils.ImageFetch;

public class PaymentFinishedActivity extends AppCompatActivity implements ExtraIntentKeys {

  private final String TITLE = "Resumo do Pagamento";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_finished);

    configView();
  }

  private void configView() {
    setTitle(TITLE);
    Intent intent = getIntent();
    Meal meal = (Meal) intent.getSerializableExtra(MEAL_KEY);

    bindViewDetails(meal);

    new ImageFetch((ImageView) findViewById(R.id.payment_resume_image))
      .execute(meal.getImage());

    Button button = findViewById(R.id.payment_finished_button);

    button.setOnClickListener(view -> {
      Intent intentTo = new Intent(this, HomeActivity.class);
      startActivity(intentTo);
    });
  }

  private void bindViewDetails(Meal meal) {
    new ImageFetch((ImageView) findViewById(R.id.payment_resume_image))
      .execute(meal.getImage());

    TextView description = findViewById(R.id.payment_resume_meal_description);
    description.setText(meal.getDescription());

    TextView title = findViewById(R.id.payment_resume_meal_title);
    title.setText(meal.getName());

    TextView price = findViewById(R.id.payment_resume_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));
  }
}