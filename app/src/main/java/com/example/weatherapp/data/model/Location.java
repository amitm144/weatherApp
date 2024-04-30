package com.example.weatherapp.data.model;

public class Location {
    private String name;
    private String temperature;

    public Location() {
    }

    public String getName() {
        return name;
    }

    public Location setName(String name) {
        this.name = name;
        return this;
    }

    public String getTemperature() {
        return temperature;
    }

    public Location setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}