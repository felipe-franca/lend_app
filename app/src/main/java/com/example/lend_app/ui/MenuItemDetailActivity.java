package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.adapters.AvailableTimesAdapter;
import com.example.lend_app.model.AvailableTimes;
import com.example.lend_app.model.DraftReservation;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Meal;
import com.example.lend_app.model.Restaurant;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.AvailableTimesService;
import com.example.lend_app.retrofit.service.ReservationsService;
import com.example.lend_app.utils.CurrencyFormatter;
import com.example.lend_app.utils.ImageFetch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuItemDetailActivity extends AppCompatActivity implements ExtraIntentKeys {
  private List<AvailableTimes> availableTimesList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu_item_detail);
    configView();
  }

  private void configView() {

    ListView listView = findViewById(R.id.menu_detail_available_times);

    Intent intent = getIntent();
    Meal meal = (Meal) intent.getSerializableExtra(MEAL_KEY);
    Restaurant restaurant = (Restaurant) intent.getSerializableExtra(RESTAURANT_KEY);

    setTitle(meal.getName());

    new ImageFetch((ImageView) findViewById(R.id.menu_detail_image))
      .execute(meal.getImage());

    TextView title = findViewById(R.id.menu_detail_title);
    title.setText(meal.getName());

    TextView description = findViewById(R.id.menu_detail_description);
    description.setText(meal.getDescription());

    TextView price = findViewById(R.id.menu_detail_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));

    chargeAvailableTimes(listView, meal, restaurant);
    handleSubmit(listView, meal);
  }

  private void chargeAvailableTimes(ListView listView, Meal meal, Restaurant restaurant) {
    AvailableTimesService service = new ApiClient().getAvailableTimesService();
    Call<List<AvailableTimes>> call = service.getAvailableTimes(restaurant.getId(), meal.getId());

    call.enqueue(new Callback<List<AvailableTimes>>() {
      @Override
      public void onResponse(Call<List<AvailableTimes>> call, Response<List<AvailableTimes>> response) {
        availableTimesList = response.body();
        listView.setAdapter(new AvailableTimesAdapter(availableTimesList, MenuItemDetailActivity.this));
      }

      @Override
      public void onFailure(Call<List<AvailableTimes>> call, Throwable t) {
        Toast.makeText(MenuItemDetailActivity.this, "Não foi possivel obter os horário disponíoveis via api!", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void handleSubmit(ListView listView, Meal meal) {
    Button button = findViewById(R.id.menu_detail_reserv_button);

    button.setOnClickListener(view -> {
      List<CheckBox> checkedList = new ArrayList<>();

      for (int i = 0; i < listView.getCount(); i++) {
        View aView = listView.getChildAt(i);
        CheckBox checkBox = aView.findViewById(availableTimesList.get(i).getId());

        if (checkBox.isChecked()) checkedList.add(checkBox);
      }

      if (checkedList.isEmpty()) {
        Toast.makeText(this, "Selecione ao menos um horário", Toast.LENGTH_SHORT).show();
        return;
      }

      postDraftReservation(checkedList, meal);
    });
  }

  private void postDraftReservation(List<CheckBox> checkedsList, Meal meal) {
    ArrayList<Integer> availableTimesIdList = new ArrayList<>();

    for (int i = 0; i < checkedsList.size(); i++) {
      availableTimesIdList.add(checkedsList.get(i).getId());
    }

    DraftReservation draftReservation = new DraftReservation(
      1,
      "felipe.franca@mail.com",
      1,
      availableTimesIdList
    );

    ReservationsService service = new ApiClient().getReservationsService();
    Call<DraftReservation> call = service.createDraftReservation(draftReservation);

    call.enqueue(new Callback<DraftReservation>() {
      @Override
      public void onResponse(Call<DraftReservation> call, Response<DraftReservation> response) {
        DraftReservation dReservation = response.body();

        if (dReservation.getId() < 1) {
          Toast.makeText(
            MenuItemDetailActivity.this,
            "Erro ao criar a reserva. Tente novamente em instantes.",
            Toast.LENGTH_SHORT).show();
          return;
        }

        next(meal);
      }

      @Override
      public void onFailure(Call<DraftReservation> call, Throwable t) {
        Toast.makeText(
          MenuItemDetailActivity.this,
          "Erro ao criar a reserva. Tente novamente em instantes.",
          Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void next(Meal meal) {
    Intent newIntent = new Intent(MenuItemDetailActivity.this, PaymentActivity.class);
    newIntent.putExtra(MEAL_KEY, meal);
    startActivity(newIntent);
  }
}