package com.example.weatherapp.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.RvMyLocationsBinding;
import com.example.weatherapp.data.model.Location;
import java.util.List;

public class RecycleView_MyLocations_Adapter extends RecyclerView.Adapter<RecycleView_MyLocations_Adapter.LocationHolder> {

    private List<Location> myLocations;
    private RecycleView_MyLocations_Adapter.OnLocationClickListener onClickListener;

    public RecycleView_MyLocations_Adapter(List<Location> myLocations) {
        this.myLocations = myLocations;
    }

    public void updateList(List<Location> myLocations) {
        this.myLocations = myLocations;
        notifyDataSetChanged();
    }

    public List<Location> getMyLocations() {
        return this.myLocations;
    }

    public void setOnLocationClickListener(RecycleView_MyLocations_Adapter.OnLocationClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecycleView_MyLocations_Adapter.LocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_my_locations, viewGroup, false);
        return new RecycleView_MyLocations_Adapter.LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_MyLocations_Adapter.LocationHolder holder, int position) {
        Location location = getItem(position);
        holder.binding.itemLocation.setText(location.getName());
        holder.binding.itemTemperature.setText(location.getTemperature() == null ? "?" : location.getTemperature());
    }

    @Override
    public int getItemCount() {
        return myLocations == null ? 0 : myLocations.size();
    }

    private Location getItem(int position) {
        return myLocations.get(position);
    }

    public interface OnLocationClickListener {
        void onClick(View view, Location location, int position);
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        private final RvMyLocationsBinding binding;

        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvMyLocationsBinding.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(view, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
