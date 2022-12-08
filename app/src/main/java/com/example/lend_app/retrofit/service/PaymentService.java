package com.example.lend_app.retrofit.service;

import com.example.lend_app.model.Payment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PaymentService {
  @POST("/payment/new")
  Call<Payment> createPayment(@Body Payment payment);
}
