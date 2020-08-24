package com.example.weatherapptest.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.weatherapptest.model.fivedayweather.FiveDayResponse;
import com.example.weatherapptest.model.fivedayweather.ItemHourly;
import com.example.weatherapptest.utils.ListTypeConverters;

import java.util.List;

@Entity(tableName = "CurrentWeatherData")
public class CurrentWeatherData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "temperature")
    private Double temp;

    @ColumnInfo(name = "minimum temperature")
    private Double tempMin;

    @ColumnInfo(name = "maximum temperature")
    private Double tempMax;

    private Double pressure;

    private Integer humidity;

    private Double wind;

    private Integer visibility;

    private Double feelsLike;

    private Double sunrise;

    private int sea_level;

    private String description;

//    @TypeConverters(ListTypeConverters.class)
//    public List<ItemHourly> list;
//
//
//    public List<ItemHourly> getList() {
//        return list;
//    }
//
//    public void setList(List<ItemHourly> list) {
//        this.list = list;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
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

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getSunrise() {
        return sunrise;
    }

    public void setSunrise(Double sunrise) {
        this.sunrise = sunrise;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }
}
