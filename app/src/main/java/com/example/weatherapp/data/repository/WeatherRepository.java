package com.example.weatherapp.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.data.model.Location;
import com.example.weatherapp.data.remote.FireBaseApi;
import com.example.weatherapp.data.remote.SearchLocationApi;
import com.example.weatherapp.data.remote.WeatherApi;
import java.util.List;

public class WeatherRepository {
    private final WeatherApi weatherApi;
    private final SearchLocationApi searchLocationApi;
    private final FireBaseApi fireBaseApi;

    public WeatherRepository() {
        this.weatherApi = new WeatherApi();
        this.searchLocationApi = new SearchLocationApi();
        this.fireBaseApi = new FireBaseApi();
    }

    public void getLocationsWeather(Location location, Context context ,  WeatherApi.TempFetchingCallback callback) {
        weatherApi.getLocationsWeather(location, context , callback);
    }

    public void getForecasts(Location location, Context context, WeatherApi.ForecastFetchingCallback callback) {
        weatherApi.getForecasts(location, context, callback);
    }

    public void getLocations(String query, Context context , SearchLocationApi.RetrieveSearchCallback callback) {
        searchLocationApi.getLocations(query, context , callback );
    }

    public void saveToMyLocations(Location location ) {
        fireBaseApi.saveToMyLocations(location);
    }

    public void retrieveMyLocations(FireBaseApi.RetrieveLocationsCallback callback) {
        fireBaseApi.retrieveMyLocations(callback);
    }

    public void updateMyLocationsOrder(List<Location> locations) {
        fireBaseApi.updateLocationOrder(locations);
    }
}