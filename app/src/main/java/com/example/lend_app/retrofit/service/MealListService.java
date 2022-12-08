package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealListService {
  @GET("/restaurants/{id}/meals")
  Call<List<Meal>> getMeals(@Path("id") int id);
}
