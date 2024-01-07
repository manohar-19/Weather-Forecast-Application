package com.manu.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manu.weather.model.WeatherForecast;
import com.manu.weather.model.WeatherForecastFinal;
import com.manu.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    @Value("${weather.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(RestTemplateBuilder restTemplateBuilder, WeatherService weatherService) {
        this.restTemplate = restTemplateBuilder.build();
        this.weatherService = weatherService;
    }

    @RequestMapping("/forecast")
    public String getWeatherForecast(@RequestParam(value = "cityName", required = false) String cityName, Model model) throws JsonProcessingException {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey + "&cnt=1";
        String defaultUrl = "https://api.openweathermap.org/data/2.5/forecast?q=davangere&appid=" + apiKey + "&cnt=1";
        WeatherForecast weatherForecast = null;
        WeatherForecastFinal weatherForecastFinal = null;

        // for initial load of the city if no city name is given
        if (cityName == null) {
            weatherForecast = restTemplate.getForObject(defaultUrl, WeatherForecast.class);
        } else {
            weatherForecast = restTemplate.getForObject(apiUrl, WeatherForecast.class);
        }
        weatherForecastFinal = weatherService.getFinalValues(weatherForecast);
        model.addAttribute("forecast", weatherForecastFinal);
        model.addAttribute("isError", false);
        return "index";
    }

}
