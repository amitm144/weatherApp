package com.example.weatherapp.data.model;

public class Forecast {
    private String img;
    private String header;
    private String secondary;
    private String sign;

    public Forecast() {
    }

    public String getImg() {
        return img;
    }

    public Forecast setImg(String img) {
        this.img = img;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Forecast setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getSecondary() {
        return secondary;
    }

    public Forecast setSecondary(String secondary) {
        this.secondary = secondary;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public Forecast setSign(String sign) {
        this.sign = sign;
        return this;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "img='" + img + '\'' +
                ", header='" + header + '\'' +
                ", secondary='" + secondary + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
