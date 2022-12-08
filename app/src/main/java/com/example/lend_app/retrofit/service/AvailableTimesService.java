package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.AvailableTimes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AvailableTimesService {
  @GET("/restaurants/{restId}/available-times/{mealId}")
  Call<List<AvailableTimes>> getAvailableTimes(@Path("restId") int restId, @Path("mealId") int mealId);
}
