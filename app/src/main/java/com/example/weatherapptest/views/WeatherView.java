package com.example.weatherapptest.views;

import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;
import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;

public interface WeatherView {

    void showProgress(boolean show);

    void showCurrentWeather(CurrentWeatherResponse currentWeatherResponse);

    void showFiveDaysWeather(FiveDayResponse fiveDayResponse);

    void showError(String error);

}
