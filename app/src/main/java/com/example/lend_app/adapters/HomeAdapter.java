package com.example.lend_app.adapters;

import static com.example.lend_app.model.ReservatioInterface.STATUS_FINISHED;
import static com.example.lend_app.model.ReservatioInterface.STATUS_PENDING;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lend_app.R;
import com.example.lend_app.model.ReservationResume;
import com.example.lend_app.utils.ImageFetch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends BaseAdapter {
  private final List<ReservationResume> reservations;
  private final Context context;

  public HomeAdapter(List<ReservationResume> reservations, Context context) {
    this.reservations = reservations;
    this.context = context;
  }

  @Override
  public int getCount() {
    return reservations.size();
  }

  @Override
  public Object getItem(int pos) {
    return reservations.get(pos);
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int pos, View view, ViewGroup viewGroup) {
    View aView = LayoutInflater.from(context).inflate(R.layout.home_card_reservation, viewGroup, false);
    ReservationResume aReservation = reservations.get(pos);

    bindTitle(aView, aReservation);
    bindDate(aView, aReservation);
    bindStatus(aView, aReservation);
    bindMealDescription(aView, aReservation);
    bindImage(aView, aReservation);

    return aView;
  }

  private void bindTitle(View view, ReservationResume reservation) {
    TextView title = view.findViewById(R.id.home_card_title);
    title.setText(reservation.getRestaurantName());
  }

  private void bindDate(View view, ReservationResume reservation) {
    TextView viewDate = view.findViewById(R.id.home_card_date);
    SimpleDateFormat sdf = new SimpleDateFormat("dd M, yyyy hh:mm");
    Date reservationDate = Calendar.getInstance().getTime();

    String date = sdf.format(reservationDate);
    viewDate.setText(reservation.getReservationDate().toString());
  }

  private void bindStatus(View view, ReservationResume reservation) {
    TextView status = view.findViewById(R.id.home_card_status);

    if (reservation.getReservationStatus().equals(STATUS_PENDING)) {
      status.setTextColor(Color.parseColor("#cf2322"));
      status.setText("Pendente");
    } else if (reservation.getReservationStatus().equals(STATUS_FINISHED)) {
      status.setTextColor(Color.parseColor("#a4a4a4"));
      status.setText("Finalizado");
    } else {
      status.setTextColor(Color.parseColor("#669900"));
      status.setText("Confirmado");
    }
  }

  private void bindMealDescription(View view, ReservationResume reservation) {
    TextView mealDescription = view.findViewById(R.id.home_card_meal);
    mealDescription.setText(reservation.getMealTitle());
  }

  private void bindImage(View view, ReservationResume reservation) {
    new ImageFetch((ImageView) view.findViewById(R.id.home_card_image))
      .execute(reservation.getMealImage());
  }
}
