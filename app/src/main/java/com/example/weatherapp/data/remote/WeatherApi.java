package com.example.weatherapp.data.remote;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.data.model.Location;
import com.example.weatherapp.data.model.Forecast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherApi {

    public WeatherApi() {
    }

    public void getLocationsWeather(Location location, Context context , TempFetchingCallback callback) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&aqi=no&q=%s" , BuildConfig.weather_api_key, location.getName());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String temp = jsonObject.getJSONObject("current").get("temp_c").toString();
                            callback.onTempFetchingComplete(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }

    public void getForecasts(Location location, Context context, ForecastFetchingCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = String.format("https://api.weatherapi.com/v1/forecast.json?key=%s&days=5&aqi=no&alerts=no&q=%s" , BuildConfig.weather_api_key, location.getName());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String todayImg = getImg(response);
                            String todayDesc = getDesc(response);
                            List<Forecast> today = todayForecast(response);
                            List<Forecast> weekTemp = weekForecast(response , "avgtemp_c");
                            List<Forecast> weekWind = weekForecast(response, "maxwind_kph");
                            List<Forecast> weekHumidity = weekForecast(response, "avghumidity");
                            callback.onForecastFetchingComplete(todayImg, todayDesc, today, weekTemp, weekWind, weekHumidity);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }


    private List<Forecast> todayForecast(String response) throws JSONException {
        List<Forecast> today = new ArrayList<>();
        JSONArray jsonObjectToday =
                new JSONObject(response)
                        .getJSONObject("forecast")
                        .getJSONArray("forecastday")
                        .getJSONObject(0)
                        .getJSONArray("hour");

        for (int i = 0; i < jsonObjectToday.length(); i++) {
            JSONObject jsonObjectHour = jsonObjectToday.getJSONObject(i);

            String img = jsonObjectHour
                    .getJSONObject("condition")
                    .getString("icon")
                    .replace("//", "https://");

            String hour = jsonObjectHour.getString("time").split(" ")[1];
            String temp = jsonObjectHour.getString("temp_c");
            String sign = "\u00B0";

            today.add(new Forecast().setImg(img).setHeader(temp).setSecondary(hour).setSign(sign));
        }
        return today;
    }

    private List<Forecast> weekForecast(String response, String attribute) throws JSONException {
        Map<String, String> attMap= new HashMap<>();
        attMap.put("avgtemp_c","\u00B0");
        attMap.put("maxwind_kph","Km/H");
        attMap.put("avghumidity","%");
        List<Forecast> today = new ArrayList<>();

        JSONArray jsonObjectToday =
                new JSONObject(response)
                        .getJSONObject("forecast")
                        .getJSONArray("forecastday");


        for (int i = 0; i < jsonObjectToday.length(); i++) {
            String date = jsonObjectToday.getJSONObject(i).getString("date");
            JSONObject jsonObjectDay = jsonObjectToday.getJSONObject(i).getJSONObject("day");

            String img = jsonObjectDay
                    .getJSONObject("condition")
                    .getString("icon")
                    .replace("//", "https://");

            String att = jsonObjectDay.getString(attribute);
            String sign = attMap.get(attribute);

            today.add(new Forecast().setImg(img).setHeader(att).setSecondary(date).setSign(sign));
        }
        return today;
    }

    private String getImg(String response) throws JSONException {
        return new JSONObject(response)
                .getJSONObject("current")
                .getJSONObject("condition")
                .getString("icon")
                .replace("//", "https://");
    }

    private String getDesc(String response) throws JSONException {
        return new JSONObject(response)
                .getJSONObject("current")
                .getJSONObject("condition")
                .getString("text");
    }

    public interface ForecastFetchingCallback {
        void onForecastFetchingComplete(String todayImg,
                                        String todayDesc,
                                        List<Forecast> today,
                                        List<Forecast> weekTemp,
                                        List<Forecast> weekWind,
                                        List<Forecast> weekHumidity);
    }

    public interface TempFetchingCallback {
        void onTempFetchingComplete(String temp);
    }
}



