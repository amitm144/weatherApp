package com.example.weatherapp.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.databinding.ActivityLocationsListBinding;
import com.example.weatherapp.ui.utils.Animation;
import com.example.weatherapp.ui.viewmodel.LocationListViewModel;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.Objects;

public class LocationListActivity extends AppCompatActivity {
    private ActivityLocationsListBinding binding;
    private RecycleView_Location_Search_Adapter searchLocationAdapter;
    private RecycleView_MyLocations_Adapter myLocationsAdapter;
    ItemTouchHelper.SimpleCallback dragAndDropCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
            0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(myLocationsAdapter.getMyLocations(), fromPosition, toPosition);
            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // Do nothing
        }
    };
    private LocationListViewModel viewModel;
    private boolean fab = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationsListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(LocationListViewModel.class);

        observeViewModel();
        initMyLocationsDisplay();
        initSearchLocationsDisplay();

        binding.fab.setOnClickListener(view1 -> fabDisplay(fab));
        binding.addLocationBtn.setOnClickListener(view1 -> searchDisplay(true));
        binding.refreshBtn.setOnClickListener(view1 -> viewModel.refreshWeather(this));

        binding.searchLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchLocations(query, LocationListActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchLocations(newText, LocationListActivity.this);
                return false;
            }
        });
    }

    private void observeViewModel() {
        viewModel.getMyLocations().observe(this, locations -> {
            showMessage(locations.isEmpty());
            myLocationsAdapter.updateList(locations);
        });

        viewModel.getSearchResults().observe(this, locations -> {
            searchLocationAdapter.updateList(locations);
        });
    }

    private void initMyLocationsDisplay() {
        myLocationsAdapter = new RecycleView_MyLocations_Adapter(viewModel.getMyLocations().getValue());
        myLocationsAdapter.setOnLocationClickListener((view, location, position) -> {
            switchActivity(new Gson().toJson(location));
        });

        binding.myLocationList.setLayoutManager(new LinearLayoutManager(this));
        binding.myLocationList.setHasFixedSize(true);
        binding.myLocationList.setAdapter(myLocationsAdapter);

        ItemTouchHelper dragAndDrop = new ItemTouchHelper(dragAndDropCallback);
        dragAndDrop.attachToRecyclerView(binding.myLocationList);

        viewModel.fetchMyLocations(this);
    }

    private void initSearchLocationsDisplay() {
        searchLocationAdapter = new RecycleView_Location_Search_Adapter(viewModel.getSearchResults().getValue());
        searchLocationAdapter.setOnLocationClickListener((view, location, position) -> {
            viewModel.saveLocation(location, this);
            searchDisplay(false);
            fabDisplay(true);
        });

        binding.locationListSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.locationListSearch.setHasFixedSize(true);
        binding.locationListSearch.setAdapter(searchLocationAdapter);
    }

    private void searchDisplay(boolean show) {
        binding.myLocationList.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.fab.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.refreshBtn.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.addLocationBtn.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.searchLocation.setVisibility(show ? View.VISIBLE : View.GONE);
        binding.locationListSearch.setVisibility(show ? View.VISIBLE : View.GONE);
        binding.title.setVisibility(show ? View.GONE : View.VISIBLE);
        if (show) binding.message.setVisibility(View.GONE);
    }

    private void fabDisplay(boolean fabView) {
        Animation animation = new Animation();
        if (fabView) {
            animation.hideButtons(binding.refreshBtn, binding.addLocationBtn);
            animation.rotateFabIcon(binding.fab, false);
        } else {
            animation.showButtons(binding.refreshBtn, binding.addLocationBtn);
            animation.rotateFabIcon(binding.fab, true);
        }
        fab = !fabView;
    }

    private void showMessage(boolean listEmpty){
        binding.message.setVisibility(listEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            searchDisplay(false);
            fabDisplay(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.updateLocationsOrder();
    }

    private void switchActivity(String value) {
        Intent intent = new Intent(this, CurrentLocationSelectionActivity.class);
        intent.putExtra("LOCATION", value);
        startActivity(intent);
    }
}