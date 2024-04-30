package com.example.weatherapp.ui.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapp.data.model.Forecast;
import com.example.weatherapp.data.model.Location;
import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.data.repository.WeatherRepository;
import java.util.List;

public class CurrentLocationSelectionViewModel extends ViewModel {
    private final WeatherRepository weatherRepository;
    private MutableLiveData<String> todayImg = new MutableLiveData<>();
    private MutableLiveData<String> todayDesc = new MutableLiveData<>();
    private MutableLiveData<List<Forecast>> todayForecast = new MutableLiveData<>();
    private MutableLiveData<List<Forecast>> weekTempForecast = new MutableLiveData<>();
    private MutableLiveData<List<Forecast>> weekWindForecast = new MutableLiveData<>();
    private MutableLiveData<List<Forecast>> weekHumidityForecast = new MutableLiveData<>();

    public CurrentLocationSelectionViewModel() {
        weatherRepository = new WeatherRepository();
    }

    public LiveData<String> getTodayImg() {
        return todayImg;
    }

    public LiveData<String> getTodayDesc() {
        return todayDesc;
    }

    public LiveData<List<Forecast>> getTodayForecast() {
        return todayForecast;
    }

    public LiveData<List<Forecast>> getWeekTempForecast() {
        return weekTempForecast;
    }

    public LiveData<List<Forecast>> getWeekWindForecast() {
        return weekWindForecast;
    }

    public LiveData<List<Forecast>> getWeekHumidityForecast() {
        return weekHumidityForecast;
    }

    public void fetchForecast(Location location, Context context) {
        weatherRepository.getForecasts(location, context, new WeatherApi.ForecastFetchingCallback() {
            @Override
            public void onForecastFetchingComplete(String img, String desc, List<Forecast> today, List<Forecast> weekTemp, List<Forecast> weekWind, List<Forecast> weekHumidity) {
                todayImg.setValue(img);
                todayDesc.setValue(desc);
                todayForecast.setValue(today);
                weekTempForecast.setValue(weekTemp);
                weekWindForecast.setValue(weekWind);
                weekHumidityForecast.setValue(weekHumidity);
            }
        });
    }
}