package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.DraftReservation;
import com.example.lend_app.model.Reservation;
import com.example.lend_app.model.ReservationResume;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservationsService {

  @GET("/reservations/my-reservations/{email}")
  Call<List<ReservationResume>> getReservations(@Path("email") String email);

  @POST("/reservations/new")
  Call<DraftReservation> createDraftReservation(@Body DraftReservation draftReservation);
}
