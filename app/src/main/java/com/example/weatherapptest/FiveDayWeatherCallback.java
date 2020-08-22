package com.example.weatherapptest;

import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;

public interface FiveDayWeatherCallback {

    void onSuccess(FiveDayResponse fiveDayResponse);

    void onFailure();
}
