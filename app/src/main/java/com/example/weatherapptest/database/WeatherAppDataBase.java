package com.example.weatherapptest.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapptest.utils.ListTypeConverters;

@Database(entities = {CurrentWeatherData.class}, version = 1, exportSchema = false)
@TypeConverters({ListTypeConverters.class})
public abstract class WeatherAppDataBase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
