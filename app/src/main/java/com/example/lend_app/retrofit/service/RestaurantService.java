package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {

  @GET("/restaurants")
  Call<List<Restaurant>> getRestaurants();
}
