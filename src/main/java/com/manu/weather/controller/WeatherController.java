package com.manu.weather.controller;

import com.manu.weather.model.WeatherForecast;
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

    @Autowired
    public WeatherController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/forecast")
    public String getWeatherForecast(@RequestParam("cityName") String cityName, Model model) {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey+"&cnt=5";

        WeatherForecast weatherForecast = restTemplate.getForObject(apiUrl,WeatherForecast.class);

        model.addAttribute("forecast", weatherForecast);

//        return "forecast";
        return "new-template";
    }
    @RequestMapping("/")
    public String showForm(){
        return "index";
    }
}
