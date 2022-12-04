package com.example.lend_app.retrofit;

import com.example.lend_app.model.ReservationResume;
import com.example.lend_app.retrofit.service.AuthService;
import com.example.lend_app.retrofit.service.MealListService;
import com.example.lend_app.retrofit.service.ReservationsService;
import com.example.lend_app.retrofit.service.RestaurantService;
import com.example.lend_app.retrofit.service.AvailableTimesService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

  private final RestaurantService restaurantService;
  private final MealListService mealListService;
  private final AvailableTimesService availableTimesService;
  private final ReservationsService reservationsResume;
  private final AuthService authService;

  public ApiClient() {
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl("http://192.168.0.107:3000")
      .addConverterFactory(GsonConverterFactory.create())
      .build();

    restaurantService = retrofit.create(RestaurantService.class);
    mealListService = retrofit.create(MealListService.class);
    availableTimesService = retrofit.create(AvailableTimesService.class);
    reservationsResume = retrofit.create(ReservationsService.class);
    authService = retrofit.create(AuthService.class);
  }

  public RestaurantService getRestaurantService() {
    return restaurantService;
  }

  public MealListService getMealListService() { return mealListService; }

  public AvailableTimesService getAvailableTimesService() { return availableTimesService; }

  public ReservationsService getReservationsResume() { return reservationsResume; }

  public AuthService getAuthService() { return authService; }
}
