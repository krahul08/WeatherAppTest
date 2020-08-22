package com.example.weatherapptest.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;
    private WeatherAppDataBase weatherAppDataBase;


    public DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        weatherAppDataBase = Room.databaseBuilder(mCtx, WeatherAppDataBase.class, "WeatherAppDataBase").build();
    }


    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public WeatherAppDataBase getAppDatabase() {
        return weatherAppDataBase;
    }
}
