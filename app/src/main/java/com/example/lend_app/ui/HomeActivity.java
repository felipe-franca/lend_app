package com.example.lend_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lend_app.R;
import com.example.lend_app.adapters.HomeAdapter;
import com.example.lend_app.asynctask.BaseAsyncTask;
import com.example.lend_app.database.Database;
import com.example.lend_app.database.dao.RoomUserDao;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.Reservation;
import com.example.lend_app.model.ReservationResume;
import com.example.lend_app.model.User;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.ReservationsService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ExtraIntentKeys {
  private final String TITLE = "Home";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    configView();
  }

  private void configView() {
    setTitle(TITLE);

    User user = getUser();

    ListView listView = findViewById(R.id.home_list_view);

    ReservationsService service = new ApiClient().getReservationsService();
    Call<List<ReservationResume>> call = service.getReservations(user.getEmail());

    call.enqueue(new Callback<List<ReservationResume>>() {
      @Override
      public void onResponse(Call<List<ReservationResume>> call, Response<List<ReservationResume>> response) {
        List<ReservationResume> reservations = response.body();
        if (reservations.isEmpty()) {
          Toast.makeText(HomeActivity.this, "Nenhuma reserva Encontrada !", Toast.LENGTH_LONG).show();
        } else {
          listView.setAdapter(new HomeAdapter(reservations, HomeActivity.this));
        }
      }

      @Override
      public void onFailure(Call<List<ReservationResume>> call, Throwable t) {
        Toast.makeText(
          HomeActivity.this,
          "Ocorreu um erro ao buscar reservas. Tente novamente mais tarde",
          Toast.LENGTH_LONG).show();
      }
    });

    FloatingActionButton fb = findViewById(R.id.home_floating_action_button);

    fb.setOnClickListener(view -> {
      Intent intentTo = new Intent(HomeActivity.this, RestaurantListActivity.class);
      startActivity(intentTo);
    });
  }

  private User getUser() {
    RoomUserDao dao = Room.databaseBuilder(this, Database.class, "lend.db")
      .allowMainThreadQueries()
      .build()
      .getRoomUserDao();

    return dao.get();
  }
}