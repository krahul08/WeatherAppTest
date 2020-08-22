package com.example.weatherapptest;

import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;

public interface CurrentWeatherCallback {

    void onSuccess(CurrentWeatherResponse currentWeatherResponse);

    void onFailure();
}
