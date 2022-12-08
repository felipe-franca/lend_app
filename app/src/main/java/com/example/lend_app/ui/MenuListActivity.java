package com.example.lend_app.ui;

import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.model.Meal;
import com.example.lend_app.model.Restaurant;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.asynctask.BaseAsyncTask;
import com.example.lend_app.adapters.MenuListAdapter;
import com.example.lend_app.retrofit.service.MealListService;

import java.util.List;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class MenuListActivity extends AppCompatActivity implements ExtraIntentKeys {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu_list);
    configListView();
  }

  private void configListView() {
    ListView listView = findViewById(R.id.activity_menu_list_view);

    Intent intent = getIntent();
    Restaurant restaurant = (Restaurant) intent.getSerializableExtra(RESTAURANT_KEY);

    setTitle(restaurant.getTitle());

    MealListService service = new ApiClient().getMealListService();
    Call<List<Meal>> call = service.getMeals(restaurant.getId());

    new BaseAsyncTask<>(() -> {
      try {
        Response<List<Meal>> response = call.execute();
        return response.body();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }, (List<Meal> mealsList) -> {
      if (mealsList == null) {
        Toast.makeText(this, "Não foi possivel buscar os pratos via api ", Toast.LENGTH_SHORT).show();
      } else {
        listView.setAdapter(new MenuListAdapter(mealsList, this));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
          Intent intentTo = new Intent(MenuListActivity.this, MenuItemDetailActivity.class);
          intentTo.putExtra(MEAL_KEY, mealsList.get(i));
          intentTo.putExtra(RESTAURANT_KEY, restaurant);
          startActivity(intentTo);
        });
      }
    }).execute();
  }
}