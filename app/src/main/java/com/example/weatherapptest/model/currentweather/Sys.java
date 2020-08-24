package com.example.weatherapptest.model.currentweather;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private long sunrise;

    @SerializedName("sunset")
    private long sunset;

    @SerializedName("message")
    private double message;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public double getMessage() {
        return message;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public void setMessage(double message) {
        this.message = message;
    }


}
