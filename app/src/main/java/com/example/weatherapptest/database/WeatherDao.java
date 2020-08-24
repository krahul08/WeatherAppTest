package com.example.weatherapptest.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM CurrentWeatherData")
    List<CurrentWeatherData> getAll();

    @Insert
    void insertAll(CurrentWeatherData weatherData);

    @Update
    void update(CurrentWeatherData weatherData);

}
