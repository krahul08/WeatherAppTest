package com.example.weatherapptest.provider;

import com.example.weatherapptest.CurrentWeatherCallback;
import com.example.weatherapptest.FiveDayWeatherCallback;

public interface WeatherProvider {

    void getCurrentWeather(String city, String apiKey, CurrentWeatherCallback currentWeatherCallback);

    void getFiveDayWeather(String city, String apiKey, FiveDayWeatherCallback fiveDayWeatherCallback);

}
