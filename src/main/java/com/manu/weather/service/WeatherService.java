package com.manu.weather.service;

import com.manu.weather.model.WeatherForecast;
import com.manu.weather.model.WeatherForecastFinal;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.Objects;

@Service
public class WeatherService {

    //convert Temperature from Kelvin to Celsius
    public static double convertToCelsius(double val) {
        // Using DecimalFormat
        val = val - 273.15;
        return val;
    }

    public static String roundOfTwoDecimal(double val) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(val);
    }

    public WeatherForecastFinal getFinalValues(WeatherForecast weatherForecast){
        double temp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTemp();
        temp = convertToCelsius(temp);
        String tempString = roundOfTwoDecimal(temp);

        double maxTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTempMax();
        maxTemp = convertToCelsius(maxTemp);
        String maxTempString = roundOfTwoDecimal(maxTemp);

        double minTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTempMin();
        minTemp = convertToCelsius(minTemp);
        String minTempString = roundOfTwoDecimal(minTemp);

        double feelsLikeTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getFeelsLike();
        feelsLikeTemp = convertToCelsius(feelsLikeTemp);
        String feelsLikeTempString = roundOfTwoDecimal(feelsLikeTemp);

        return new WeatherForecastFinal(weatherForecast,tempString,maxTempString,minTempString,feelsLikeTempString);
    }
}
