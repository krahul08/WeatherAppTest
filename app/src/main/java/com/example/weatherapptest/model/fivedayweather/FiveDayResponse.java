package com.example.weatherapptest.model.fivedayweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FiveDayResponse {

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private double message;

    @SerializedName("list")
    private List<ItemHourly> list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

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
