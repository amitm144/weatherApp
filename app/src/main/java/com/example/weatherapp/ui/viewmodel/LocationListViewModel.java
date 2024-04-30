package com.example.weatherapp.ui.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapp.data.model.Location;
import com.example.weatherapp.data.repository.WeatherRepository;
import java.util.List;

public class LocationListViewModel extends ViewModel {
    private final WeatherRepository weatherRepository;
    private MutableLiveData<List<Location>> myLocations = new MutableLiveData<>();
    private MutableLiveData<List<Location>> searchResults = new MutableLiveData<>();

    public LocationListViewModel() {
        weatherRepository = new WeatherRepository();
    }

    public LiveData<List<Location>> getMyLocations() {
        return myLocations;
    }

    public LiveData<List<Location>> getSearchResults() {
        return searchResults;
    }

    public void fetchMyLocations(Context context) {
        weatherRepository.retrieveMyLocations(locations -> {
            myLocations.setValue(locations);
            refreshWeather(context);
        });
    }

    public void searchLocations(String query, Context context) {
        weatherRepository.getLocations(query, context, locations -> searchResults.setValue(locations));
    }

    public void saveLocation(Location location, Context context) {
        weatherRepository.getLocationsWeather(location, context, temp -> {
            location.setTemperature(temp);
            myLocations.getValue().add(location);
            weatherRepository.saveToMyLocations(location);
            myLocations.postValue(myLocations.getValue());
        });
    }
    public void updateLocationsOrder() {
        weatherRepository.updateMyLocationsOrder(myLocations.getValue());
    }

    public void refreshWeather(Context context) {
        for (Location location : myLocations.getValue()) {
            weatherRepository.getLocationsWeather(location, context, temp -> {
                location.setTemperature(temp);
            });
        }
    }
}