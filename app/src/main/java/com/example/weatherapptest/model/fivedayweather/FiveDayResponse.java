package com.example.weatherapptest.model.fivedayweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FiveDayResponse {


    @SerializedName("message")
    private double message;

    @SerializedName("list")
    private List<ItemHourly> list;


    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public List<ItemHourly> getList() {
        return list;
    }

    public void setList(List<ItemHourly> list) {
        this.list = list;
    }
}
