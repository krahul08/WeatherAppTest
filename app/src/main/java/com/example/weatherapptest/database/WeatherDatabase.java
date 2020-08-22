package com.example.weatherapptest.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeatherDatabase {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "temperature")
    private Double temp;

    @ColumnInfo(name = "minimum temperature")
    private Double tempMin;

    @ColumnInfo(name = "maximum temperature")
    private Double tempMax;

//    private Integer pressure;
//
//    private Integer humidity;
//
//    private Integer seaLevel;
//
//    private Integer grndLevel;


    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
