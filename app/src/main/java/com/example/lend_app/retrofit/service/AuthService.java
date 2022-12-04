package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

  @POST("/sign-in")
  Call<User> signIn(@Body User user);

  @POST("/register")
  Call<User> register(@Body User user);
}
