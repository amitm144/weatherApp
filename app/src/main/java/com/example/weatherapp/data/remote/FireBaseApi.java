package com.example.weatherapp.data.remote;

import androidx.annotation.NonNull;
import com.example.weatherapp.data.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FireBaseApi {
    public FireBaseApi() {
    }

    public void saveToMyLocations(Location location) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myLocationsRef = database.getReference("MyLocations");
        myLocationsRef.child(location.getName()).setValue(location);
    }

    public void retrieveMyLocations(RetrieveLocationsCallback callback) {
        List<Location> rv = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myLocationsRef = database.getReference("MyLocations");

        myLocationsRef.orderByPriority().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rv.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Location location = child.getValue(Location.class);
                    rv.add(location);
                }
                callback.onLocationRetrieveComplete(rv);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void updateLocationOrder(List<Location> locations) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myLocationsRef = database.getReference("MyLocations");

        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            myLocationsRef.child(location.getName()).setValue(location);
            myLocationsRef.child(location.getName()).setPriority(i);
        }
    }

    public interface RetrieveLocationsCallback {
        void onLocationRetrieveComplete(List<Location> locations);
    }
}