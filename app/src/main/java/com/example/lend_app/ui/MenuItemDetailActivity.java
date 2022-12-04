package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.adapters.AvailableTimesAdapter;
import com.example.lend_app.asynctask.BaseAsyncTask;
import com.example.lend_app.model.AvailableTimes;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Meal;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.AvailableTimesService;
import com.example.lend_app.utils.CurrencyFormatter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MenuItemDetailActivity extends AppCompatActivity implements ExtraIntentKeys {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu_item_detail);
    configActivity();
  }

  private void configActivity() {
    ListView listView = findViewById(R.id.menu_detail_available_times);

    Intent intent = getIntent();
    Meal meal = (Meal) intent.getSerializableExtra(MEAL_KEY);

    setTitle(meal.getName());
    TextView price = findViewById(R.id.menu_detail_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));

    AvailableTimesService service = new ApiClient().getAvailableTimesService();
    Call<List<AvailableTimes>> call = service.getAvailableTimes(meal.getId());

    new BaseAsyncTask<>(() -> {
      try {
        Response<List<AvailableTimes>> response = call.execute();
        return response.body();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }, (List<AvailableTimes> availableTimesList) -> {
      if (availableTimesList == null)
        Toast.makeText(this, "Não foi possivel obter os horário via api!", Toast.LENGTH_SHORT).show();

      listView.setAdapter(new AvailableTimesAdapter(availableTimesList, this));
    }).execute();

    Button button = findViewById(R.id.menu_detail_reserv_button);

    button.setOnClickListener(view -> {
      Intent newIntent = new Intent(MenuItemDetailActivity.this, PaymentActivity.class);
      newIntent.putExtra(MEAL_KEY, meal);
      startActivity(newIntent);
    });

  }
}