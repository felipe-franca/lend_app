package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.AvailableTimes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AvailableTimesService {
  @GET("/{id}/availables")
  Call<List<AvailableTimes>> getAvailableTimes(@Path("id") int id);
}
