package com.example.weatherapptest.provider;

import com.example.weatherapptest.CurrentWeatherCallback;
import com.example.weatherapptest.FiveDayWeatherCallback;
import com.example.weatherapptest.api.WeatherApi;
import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;
import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.utils.RetrofitServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherProviderImpl implements WeatherProvider {

    private WeatherApi weatherApi;
    private RetrofitServiceProvider serviceProvider;
    private Call<CurrentWeatherResponse> currentWeatherResponseCall;
    private Call<FiveDayResponse> fiveDayResponseCall;


    public WeatherProviderImpl() {
        serviceProvider = new RetrofitServiceProvider();
        weatherApi = serviceProvider.getRetrofit().create(WeatherApi.class);
    }


    @Override
    public void getCurrentWeather(String city, String apiKey, final CurrentWeatherCallback currentWeatherCallback) {
        currentWeatherResponseCall = weatherApi.getCurrentWeather(city, apiKey);
        currentWeatherResponseCall.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                currentWeatherCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                currentWeatherCallback.onFailure();
            }
        });


    }

    @Override
    public void getFiveDayWeather(String city, String apiKey, final FiveDayWeatherCallback fiveDayWeatherCallback) {
        fiveDayResponseCall = weatherApi.getFiveDaysWeather(city, apiKey);
        fiveDayResponseCall.enqueue(new Callback<FiveDayResponse>() {
            @Override
            public void onResponse(Call<FiveDayResponse> call, Response<FiveDayResponse> response) {
                fiveDayWeatherCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FiveDayResponse> call, Throwable t) {
                fiveDayWeatherCallback.onFailure();

            }
        });


    }
}
