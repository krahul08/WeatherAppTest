package com.example.weatherapptest.presenter;

public interface WeatherPresenter {

    void getCurrentWeather(String city, String apiKey);

    void getFiveDayWeather(String city, String apiKey);

}
