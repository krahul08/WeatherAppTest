package com.example.weatherapptest.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM weatherDatabase")
    List<WeatherDatabase> getAll();


    @Insert
    void insertAll(WeatherDatabase weatherData);

}
