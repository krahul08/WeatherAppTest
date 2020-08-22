package com.example.weatherapptest.presenter;

import com.example.weatherapptest.CurrentWeatherCallback;
import com.example.weatherapptest.FiveDayWeatherCallback;
import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;
import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.provider.WeatherProvider;
import com.example.weatherapptest.views.WeatherView;

public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherView weatherView;
    private WeatherProvider weatherProvider;


    public WeatherPresenterImpl(WeatherView weatherView, WeatherProvider weatherProvider) {
        this.weatherView = weatherView;
        this.weatherProvider = weatherProvider;

    }

    @Override
    public void getCurrentWeather(String city, String apiKey) {
        weatherView.showProgress(true);
        weatherProvider.getCurrentWeather(city, apiKey, new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeatherResponse currentWeatherResponse) {
                try {
                    weatherView.showProgress(false);
                    weatherView.showCurrentWeather(currentWeatherResponse);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {

                weatherView.showProgress(false);
                weatherView.showError("Please try again");
            }
        });

    }

    @Override
    public void getFiveDayWeather(String city, String apiKey) {
        weatherView.showProgress(true);
        weatherProvider.getFiveDayWeather(city, apiKey, new FiveDayWeatherCallback() {
            @Override
            public void onSuccess(FiveDayResponse fiveDayResponse) {
                try {
                    weatherView.showProgress(false);
                    weatherView.showFiveDaysWeather(fiveDayResponse);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {

                weatherView.showProgress(false);
                weatherView.showError("Please try again");

            }
        });

    }
}
