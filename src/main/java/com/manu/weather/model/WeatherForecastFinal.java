package com.manu.weather.model;

public class WeatherForecastFinal {
    private WeatherForecast weatherForecast;
    private String temp;
    private String maxTemp;
    private String minTemp;

    private String feelsLikeTemp;

    public WeatherForecastFinal(WeatherForecast weatherForecast, String temp, String maxTemp, String minTemp, String feelsLikeTemp) {
        this.weatherForecast = weatherForecast;
        this.temp = temp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public WeatherForecast getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(WeatherForecast weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(String feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    @Override
    public String toString() {
        return "WeatherForecastFinal{" +
                "weatherForecast=" + weatherForecast +
                ", temp='" + temp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", feelsLikeTemp='" + feelsLikeTemp + '\'' +
                '}';
    }
}
