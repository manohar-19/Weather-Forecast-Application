package com.manu.weather.model;


public class WeatherErrorResponse {
    private int cod;
    private String message;

    public WeatherErrorResponse() {
    }

    public WeatherErrorResponse(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
