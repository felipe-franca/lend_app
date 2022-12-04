package com.example.lend_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lend_app.R;
import com.example.lend_app.adapters.HomeAdapter;
import com.example.lend_app.asynctask.BaseAsyncTask;
import com.example.lend_app.model.ReservationResume;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.ReservationsService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
  private final String TITLE = "Home";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    configView();
  }

  private void configView() {
    setTitle(TITLE);

    ListView listView = findViewById(R.id.home_list_view);

    ReservationsService service = new ApiClient().getReservationsResume();
    Call<List<ReservationResume>> call = service.getReservations("felipe.paulino57@gmail.com");

    new BaseAsyncTask<>(() -> {
      try {
        Response<List<ReservationResume>> response = call.execute();
        return response.body();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }, (List<ReservationResume> reservations) -> {
      if (null == reservations) {
        Toast.makeText(this, "Não foi possivel buscar as reservas via api", Toast.LENGTH_LONG).show();
      } else {
        listView.setAdapter(new HomeAdapter(reservations, this));
      }
    }).execute();


    FloatingActionButton fb = findViewById(R.id.home_floating_action_button);

    fb.setOnClickListener(view -> {
      Intent intent = new Intent(HomeActivity.this, RestaurantListActivity.class);
      startActivity(intent);
    });
  }
}