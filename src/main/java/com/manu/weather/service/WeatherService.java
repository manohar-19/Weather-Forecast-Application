package com.manu.weather.service;

import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

@Service
public class WeatherService {

    //convert Temperature from Kelvin to Celsius
    public double convertToCelsius(double val) {
        // Using DecimalFormat
        val = val - 273.15;
        return val;
    }

    public String roundOfTwoDecimal(double val) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(val);
    }
}
