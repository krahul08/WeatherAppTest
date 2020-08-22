package com.example.weatherapptest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapptest.R;
import com.example.weatherapptest.database.DatabaseClient;
import com.example.weatherapptest.database.WeatherDatabase;
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


        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                WeatherDatabase weatherDatabase = new WeatherDatabase();
                weatherDatabase.setTemp(currentWeatherResponse.getMain().getTemp());
                weatherDatabase.setTempMax(currentWeatherResponse.getMain().getTempMax());
                weatherDatabase.setTempMin(currentWeatherResponse.getMain().getTempMin());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .weatherDao().insertAll(weatherDatabase);



                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }

        }
        SaveTask saveTask = new SaveTask();
        saveTask.execute();

    }

    @Override
    public void showFiveDaysWeather(FiveDayResponse fiveDayResponse) {


    }

    @Override
    public void showError(String error) {

    }
}
