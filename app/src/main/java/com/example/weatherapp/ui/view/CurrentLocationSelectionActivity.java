package com.example.weatherapp.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.weatherapp.databinding.ActivityCurrentLocationSelectionBinding;
import com.example.weatherapp.data.model.Location;
import com.example.weatherapp.ui.viewmodel.CurrentLocationSelectionViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class CurrentLocationSelectionActivity extends AppCompatActivity {
    private RecycleView_Forecast_Adapter todayForecastAdapter, attributesForecastAdapter;
    private ActivityCurrentLocationSelectionBinding binding;
    private Location currentLocation;
    private CurrentLocationSelectionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentLocationSelectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        currentLocation = new Gson().fromJson(intent.getStringExtra("LOCATION"), Location.class);

        viewModel = new ViewModelProvider(this).get(CurrentLocationSelectionViewModel.class);

        observeViewModel();
        init();
    }

    private void observeViewModel() {
        viewModel.getTodayImg().observe(this, img -> {
            binding.progressBar1.setVisibility(View.GONE);
            Picasso
                    .get()
                    .load(img)
                    .into(binding.todayImg);
        });

        viewModel.getTodayDesc().observe(this, desc -> {
            binding.todayDesc.setText(desc);
        });

        viewModel.getTodayForecast().observe(this, forecasts -> {
            binding.progressBar2.setVisibility(View.GONE);
            todayForecastAdapter.updateList(forecasts);
        });

        viewModel.getWeekTempForecast().observe(this, forecasts -> {
            binding.progressBar3.setVisibility(View.GONE);
            attributesForecastAdapter.updateList(forecasts);
        });

        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == binding.tempBtn.getId()) {
                    viewModel.getWeekTempForecast().observe(this, forecasts -> {
                        attributesForecastAdapter.updateList(forecasts);
                    });
                } else if (checkedId == binding.windBtn.getId()) {
                    viewModel.getWeekWindForecast().observe(this, forecasts -> {
                        attributesForecastAdapter.updateList(forecasts);
                    });
                } else if (checkedId == binding.humidityBtn.getId()) {
                    viewModel.getWeekHumidityForecast().observe(this, forecasts -> {
                        attributesForecastAdapter.updateList(forecasts);
                    });
                }
            }
        });
    }

    private void init() {
        viewModel.fetchForecast(currentLocation, this);
        initLocationDisplay();
        initTempForecastRv();
        initAttributesForecastRv();
    }

    private void initLocationDisplay() {
        binding.location.setText(currentLocation.getName());
        binding.temperature.setText(currentLocation.getTemperature());
    }

    private void initTempForecastRv() {
        todayForecastAdapter = new RecycleView_Forecast_Adapter(viewModel.getTodayForecast().getValue());
        binding.todayForecastRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.todayForecastRv.setHasFixedSize(true);
        binding.todayForecastRv.setAdapter(todayForecastAdapter);
    }

    private void initAttributesForecastRv() {
        attributesForecastAdapter = new RecycleView_Forecast_Adapter(viewModel.getWeekTempForecast().getValue());
        binding.attributesForecastRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.attributesForecastRv.setHasFixedSize(true);
        binding.attributesForecastRv.setAdapter(attributesForecastAdapter);
    }
}