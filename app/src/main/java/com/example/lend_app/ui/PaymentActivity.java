package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.lend_app.R;
import com.example.lend_app.database.Database;
import com.example.lend_app.database.dao.RoomUserDao;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Meal;
import com.example.lend_app.model.Payment;
import com.example.lend_app.model.User;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.PaymentService;
import com.example.lend_app.utils.CurrencyFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements ExtraIntentKeys {

  private final String TITLE = "Pagamento";

  private EditText cardNumber;
  private EditText expiresInMonth;
  private EditText expiresInYear;
  private EditText cvc;
  private EditText cardOwner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);

    configView();
  }

  private void configView() {
    Intent intent = getIntent();
    Meal meal = (Meal) intent.getSerializableExtra(MEAL_KEY);

    TextView price = findViewById(R.id.payment_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));

    setTitle(TITLE);

    handleSubmit(meal);
  }

  private void handleSubmit(Meal meal) {
    Button button = findViewById(R.id.payment_confirm_button);

    button.setOnClickListener(view -> {

      if (!checkFields()) {
        Toast.makeText(PaymentActivity.this, "Preencha todos os campos !", Toast.LENGTH_SHORT).show();
        return;
      }

      User user = getUser();

      Payment payment = new Payment(
        cardNumber.getText().toString(),
        expiresInMonth.getText().toString() + "/" + expiresInYear.getText().toString(),
        cvc.getText().toString(),
        cardOwner.getText().toString(),
        user.getEmail()
      );

      PaymentService service = new ApiClient().getPaymentService();
      Call<Payment> call = service.createPayment(payment);

      call.enqueue(new Callback<Payment>() {
        @Override
        public void onResponse(Call<Payment> call, Response<Payment> response) {
          Payment payment = response.body();

          if (payment.getId() < 1) {
            Toast.makeText(
              PaymentActivity.this,
              "Erro ao realizar pagamento. Tente novamente em instantes",
              Toast.LENGTH_SHORT).show();

              return;
          }

          next(meal);
        }

        @Override
        public void onFailure(Call<Payment> call, Throwable t) {
          Toast.makeText(
            PaymentActivity.this,
            "Erro ao realizar pagamento tente novamente em instantes",
            Toast.LENGTH_SHORT).show();
        }
      });

    });
  }

  private User getUser() {
    RoomUserDao dao = Room.databaseBuilder(this, Database.class, "lend.db")
      .allowMainThreadQueries()
      .build()
      .getRoomUserDao();

    return dao.get();
  }

  private Boolean checkFields() {
    cardNumber = findViewById(R.id.payment_card_number);
    expiresInMonth = findViewById(R.id.payment_card_month);
    expiresInYear = findViewById(R.id.payment_card_year);
    cvc = findViewById(R.id.payment_cvc);
    cardOwner = findViewById(R.id.payment_card_owner);

    return !cardNumber.getText().toString().equals("")
      && !expiresInMonth.getText().toString().equals("")
      && !expiresInYear.getText().toString().equals("")
      && !cvc.getText().toString().equals("")
      && !cardOwner.getText().toString().equals("");
  }

  private void next(Meal meal) {
    Intent intent = new Intent(PaymentActivity.this, PaymentFinishedActivity.class);
    intent.putExtra(MEAL_KEY, meal);
    startActivity(intent);
    finish();
  }
}