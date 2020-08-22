package com.example.weatherapptest.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherDatabase.class}, version = 1, exportSchema = false)
public abstract class WeatherAppDataBase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
