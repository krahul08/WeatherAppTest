package com.example.weatherapptest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapptest.R;
import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;
import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.presenter.WeatherPresenter;
import com.example.weatherapptest.presenter.WeatherPresenterImpl;
import com.example.weatherapptest.provider.WeatherProviderImpl;
import com.example.weatherapptest.utils.UrlConstants;

public class MainActivity extends AppCompatActivity implements WeatherView {


    private WeatherPresenter weatherPresenter;
    private String apiKey = UrlConstants.API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherPresenter = new WeatherPresenterImpl(this, new WeatherProviderImpl());
        weatherPresenter.getCurrentWeather("Raipur", apiKey);
        weatherPresenter.getFiveDayWeather("Raipur", apiKey);

    }

    @Override
    public void showProgress(boolean show) {


    }

    @Override
    public void showCurrentWeather(CurrentWeatherResponse currentWeatherResponse) {
        Toast.makeText(this, "" + currentWeatherResponse.getClouds().getAll(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showFiveDaysWeather(FiveDayResponse fiveDayResponse) {

    }

    @Override
    public void showError(String error) {

    }
}
