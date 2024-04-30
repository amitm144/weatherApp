package com.example.weatherapp.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.RvLocationSearchBinding;
import com.example.weatherapp.data.model.Location;
import java.util.List;

public class RecycleView_Location_Search_Adapter extends RecyclerView.Adapter<RecycleView_Location_Search_Adapter.LocationHolder> {

    private List<Location> locationsSearch;
    private OnLocationClickListener onClickListener;

    public RecycleView_Location_Search_Adapter(List<Location> locationsSearch) {
        this.locationsSearch = locationsSearch;
    }

    public void updateList(List<Location> locationsSearch) {
        this.locationsSearch = locationsSearch;
        notifyDataSetChanged();
    }

    public void setOnLocationClickListener(OnLocationClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Inflate the item layout for the RecyclerView
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_location_search, viewGroup, false);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        // Bind the data to the views in each item of the RecyclerView
        Location location = getItem(position);
        holder.binding.itemLocation.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return locationsSearch == null ? 0 : locationsSearch.size();
    }

    private Location getItem(int position) {
        return locationsSearch.get(position);
    }

    public List<Location> getLocationsSearch() {
        return this.locationsSearch;
    }

    public interface OnLocationClickListener {
        void onClick(View view, Location location, int position);
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        private final RvLocationSearchBinding binding;

        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvLocationSearchBinding.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
