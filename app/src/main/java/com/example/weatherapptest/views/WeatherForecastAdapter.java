package com.example.weatherapptest.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapptest.R;
import com.example.weatherapptest.model.fivedayweather.ItemHourly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ItemHourly> list;

    public WeatherForecastAdapter(Context context) {
        this.context = context;
        if (context != null) {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.forecast_list_layout, viewGroup, false);
        return new listHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final WeatherForecastAdapter.listHolder listHolder = (WeatherForecastAdapter.listHolder) holder;
        final ItemHourly categoryListData = list.get(position);
        Toast.makeText(context, "" + categoryListData.getDtTxt(), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String currentString = categoryListData.getDtTxt();
                String[] separated = currentString.split(" ");
                String date = separated[0];
                String time = separated[1];


                try {
                    SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date_format = inFormat.parse(date);
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String goal = outFormat.format(date_format);

                    listHolder.date_day.setText("" + goal);
                    Log.d("ngkn", goal);


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < categoryListData.getWeather().size(); i++) {
                    listHolder.forecast_desc.setText(categoryListData.getWeather().get(i).getDescription());
                }
                listHolder.max_temp_forecast.setText(categoryListData.getMain().getTemp() + " \u2109");

            }
        }, 1000);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<ItemHourly> list) {
        this.list = list;
    }

    public class listHolder extends RecyclerView.ViewHolder {

        TextView date_day;
        TextView forecast_desc;
        TextView max_temp_forecast;
        ImageView weather_image;


        public listHolder(View view) {
            super(view);
            date_day = view.findViewById(R.id.date_day);
            forecast_desc = view.findViewById(R.id.forecast_desc);
            max_temp_forecast = view.findViewById(R.id.max_temp_forecast);
            weather_image = view.findViewById(R.id.weather_image);
        }
    }
}
