package com.example.weatherapptest.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapptest.model.fivedayweather.ItemHourly;

import java.util.List;

@Entity(tableName = "WeatherForecast")
public class WeatherForecast {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(ItemHourly.class)
    @ColumnInfo(name = "forecast")
    private List<ItemHourly> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemHourly> getList() {
        return list;
    }

    public void setList(List<ItemHourly> list) {
        this.list = list;
    }
}
