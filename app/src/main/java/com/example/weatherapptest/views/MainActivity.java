package com.example.weatherapptest.views;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapptest.R;
import com.example.weatherapptest.database.DatabaseClient;
import com.example.weatherapptest.database.CurrentWeatherData;
import com.example.weatherapptest.model.currentweather.CurrentWeatherResponse;
import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.presenter.WeatherPresenter;
import com.example.weatherapptest.presenter.WeatherPresenterImpl;
import com.example.weatherapptest.provider.WeatherProviderImpl;
import com.example.weatherapptest.utils.UrlConstants;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherView {


    @Nullable
    @BindView(R.id.temperature_all)
    TextView temperature;
    @Nullable
    @BindView(R.id.weather_desc)
    TextView weather_desc;
    @Nullable
    @BindView(R.id.max_temp)
    TextView max_temp;
    @Nullable

    @BindView(R.id.min_temp)
    TextView min_temp;
    @Nullable

    @BindView(R.id.feels_like)
    TextView feels_like;
    @Nullable

    @BindView(R.id.wind_speed)
    TextView wind_speed;
    @Nullable

    @BindView(R.id.visibility)
    TextView visibility;
    @Nullable

    @BindView(R.id.humidity)
    TextView humidity;
    @Nullable

    @BindView(R.id.pressure)
    TextView pressure;
    @Nullable

    @BindView(R.id.sunrise)
    TextView sunrise;
    @Nullable

    @BindView(R.id.sunset)
    TextView sunset;
    @Nullable

    @BindView(R.id.weekly_weather_list)
    RecyclerView weekly_weather_list;


    private WeatherPresenter weatherPresenter;
    private CurrentWeatherData currentWeatherData;
    private WeatherForecastAdapter weatherForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherPresenter = new WeatherPresenterImpl(this, new WeatherProviderImpl());
        weatherForecastAdapter = new WeatherForecastAdapter(this);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = Objects.requireNonNull(cm).getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to wifi
                callApi();
            }
        } else {
            Toast.makeText(this, "no internet", Toast.LENGTH_SHORT).show();
            // not connected to the internet
            getDataFromDb();
        }

    }


    private void callApi() {
        weatherPresenter.getCurrentWeather(getString(R.string.city_name), UrlConstants.API_KEY);
    }


    @Override
    public void showProgress(boolean show) {

    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void showCurrentWeather(CurrentWeatherResponse currentWeatherResponse) {

        setData(currentWeatherResponse);

        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                currentWeatherData = new CurrentWeatherData();
                currentWeatherData.setTemp(currentWeatherResponse.getMain().getTemp());
                currentWeatherData.setTempMax(currentWeatherResponse.getMain().getTempMax());
                currentWeatherData.setTempMin(currentWeatherResponse.getMain().getTempMin());
                currentWeatherData.setFeelsLike(currentWeatherResponse.getMain().getFeels_like());
                currentWeatherData.setWind(currentWeatherResponse.getWind().getSpeed());
                currentWeatherData.setHumidity(currentWeatherResponse.getMain().getHumidity());
                currentWeatherData.setVisibility(currentWeatherResponse.getVisibility());
                currentWeatherData.setPressure(currentWeatherResponse.getMain().getPressure());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .weatherDao().insertAll(currentWeatherData);
                return null;
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                weatherPresenter.getFiveDayWeather(getString(R.string.city_name), UrlConstants.API_KEY);


            }

        }
        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }


    private void getDataFromDb() {
        @SuppressLint("StaticFieldLeak")
        class GetData extends AsyncTask<Void, Void, List<CurrentWeatherData>> {


            @Override
            protected List<CurrentWeatherData> doInBackground(Void... voids) {

                return DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .weatherDao()
                        .getAll();
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onPostExecute(List<CurrentWeatherData> weatherDatabases) {
                super.onPostExecute(weatherDatabases);
                for (int i = 0; i < weatherDatabases.size(); i++) {
                    pressure.setText((weatherDatabases.get(i).getPressure()) + " mbar");
                    feels_like.setText((weatherDatabases.get(i).getFeelsLike()) + " \u2109");
                    temperature.setText((weatherDatabases.get(i).getTemp()) + " \u2109");
                    max_temp.setText((weatherDatabases.get(i).getTempMax()) + " \u2109");
                    min_temp.setText((weatherDatabases.get(i).getTempMin()) + " \u2109");
                    visibility.setText((weatherDatabases.get(i).getVisibility()) + " km");
                    humidity.setText((weatherDatabases.get(i).getHumidity()) + " %");
                    wind_speed.setText((weatherDatabases.get(i).getWind()) + " km/h");
                }

            }
        }
        GetData getData = new GetData();
        getData.execute();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private void setData(CurrentWeatherResponse response) {
        temperature.setText((response.getMain().getTemp()) + " \u2109");
        max_temp.setText((response.getMain().getTempMax()) + " \u2109");
        min_temp.setText((response.getMain().getTempMin()) + " \u2109");
        visibility.setText((response.getVisibility()) + " km");
        humidity.setText((response.getMain().getHumidity()) + " %");
        wind_speed.setText((response.getWind().getSpeed()) + " km/h");
        pressure.setText((response.getMain().getPressure()) + " mbar");
        feels_like.setText("Feels" + (response.getMain().getFeels_like()) + " \u2109");

        try {
            final DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("HH:mm:ss");

            final long unixTime = response.getSys().getSunrise();
            final String formattedDtm = Instant.ofEpochSecond(unixTime)
                    .atZone(ZoneId.of("GMT-4"))
                    .format(formatter);

            System.out.println(formattedDtm);
            sunrise.setText(formattedDtm);
            sunset.setText(formattedDtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFiveDaysWeather(FiveDayResponse fiveDayResponse) {
        setForecast(fiveDayResponse);
    }

    private void setForecast(FiveDayResponse fiveDayResponse) {
        if (fiveDayResponse.getList().size() != 0) {

            weatherForecastAdapter.setData(fiveDayResponse.getList());
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            assert weekly_weather_list != null;
            weekly_weather_list.setLayoutManager(layoutManager);
            weekly_weather_list.setHasFixedSize(true);
            weekly_weather_list.setAdapter(weatherForecastAdapter);
            weatherForecastAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String error) {

    }
}
