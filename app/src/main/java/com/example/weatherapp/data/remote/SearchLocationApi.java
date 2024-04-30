package com.example.weatherapp.data.remote;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.data.model.Location;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLocationApi {
    public SearchLocationApi() {
    }

    public void getLocations(String locationPrefix, Context context, RetrieveSearchCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = String.format("https://wft-geo-db.p.rapidapi.com/v1/geo/places?limit=10&namePrefix=%s", locationPrefix);
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            List<Location> locations = fetchData(json);
                            callback.onSearchComplete(locations);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onSearchComplete(new ArrayList<>());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", BuildConfig.searc_api_key);
                params.put("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com");
                return params;
            }
        };
        queue.add(getRequest);

    }

    private List<Location> fetchData(JSONObject json) throws JSONException {
        List<Location> locations = new ArrayList<>();
        JSONArray jsonArray = json.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jLocation = jsonArray.getJSONObject(i);
            Location newLocation = new Location();

            String city = jLocation.getString("name");
            String country = jLocation.getString("country");
            newLocation.setName(city + ", " + country);
            locations.add(newLocation);
        }
        return locations;
    }

    public interface RetrieveSearchCallback {
        void onSearchComplete(List<Location> locations);
    }
}