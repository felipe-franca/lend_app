package com.example.lend_app.ui;

import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.model.Restaurant;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.asynctask.BaseAsyncTask;
import com.example.lend_app.adapters.RestaurantsListAdapter;
import com.example.lend_app.retrofit.service.RestaurantService;

import java.util.List;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RestaurantListActivity extends AppCompatActivity implements ExtraIntentKeys {
  private final String TITLE = "Restaurantes próximos";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurants_list);

    configListView();
  }

  private void configListView() {
    ListView listView = findViewById(R.id.items_list);
    setTitle(TITLE);

    RestaurantService service = new ApiClient().getRestaurantService();
    Call<List<Restaurant>> call = service.getRestaurants();

    new BaseAsyncTask<>(() -> {
      try {
        Response<List<Restaurant>> response = call.execute();
        return response.body();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }, (List<Restaurant> nRestaurantList) -> {
      if (nRestaurantList == null) {
        Toast.makeText(this, "Não foi possivel buscar os restaurantes via api ", Toast.LENGTH_SHORT).show();
      } else {
        listView.setAdapter(new RestaurantsListAdapter(nRestaurantList, RestaurantListActivity.this));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
          Intent intent = new Intent(RestaurantListActivity.this, MenuListActivity.class);
          intent.putExtra(RESTAURANT_KEY, nRestaurantList.get(i));
          startActivity(intent);
        });
      }
    }).execute();
  }
}
