package com.example.weatherapptest.api;

import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String city,
            @Query("appid") String apiKey
    );


    @GET("forecast")
    Call<FiveDayResponse> getFiveDaysWeather(
            @Query("q") String city,
            @Query("appid") String apiKey
    );
}
