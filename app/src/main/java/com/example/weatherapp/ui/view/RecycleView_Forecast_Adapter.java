package com.example.weatherapp.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.RvForecastBinding;
import com.example.weatherapp.data.model.Forecast;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecycleView_Forecast_Adapter extends RecyclerView.Adapter<RecycleView_Forecast_Adapter.LocationHolder> {

    List<Forecast> forecastList ;

    public RecycleView_Forecast_Adapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    public Forecast getItem(int position){
        return forecastList.get(position);
    }
    public void updateList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_forecast, viewGroup, false);
        return new RecycleView_Forecast_Adapter.LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        Forecast forecast = getItem(position);
        holder.binding.header.setText(forecast.getHeader());
        holder.binding.secondary.setText(forecast.getSecondary());
        holder.binding.sign.setText(forecast.getSign());
        Picasso.get().load(forecast.getImg()).into(holder.binding.image);
    }

    @Override
    public int getItemCount() {
        return forecastList == null ? 0 : forecastList.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder{
        RvForecastBinding binding;
        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvForecastBinding.bind(itemView);
        }
    }
}
