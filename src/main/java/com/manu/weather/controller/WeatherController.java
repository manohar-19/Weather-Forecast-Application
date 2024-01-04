package com.manu.weather.controller;

import com.manu.weather.model.WeatherForecast;
import com.manu.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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
    public String getWeatherForecast(@RequestParam(value = "cityName", required = false) String cityName, Model model) {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey + "&cnt=1";
        String defaultUrl = "https://api.openweathermap.org/data/2.5/forecast?q=davangere&appid=" + apiKey + "&cnt=1";

        WeatherForecast weatherForecast;
        if (cityName == null) {
            weatherForecast = restTemplate.getForObject(defaultUrl, WeatherForecast.class);
        } else {
            weatherForecast = restTemplate.getForObject(apiUrl, WeatherForecast.class);
        }

        double temp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTemp();
        temp = weatherService.convertToCelsius(temp);
        String tempString = weatherService.roundOfTwoDecimal(temp);

        double maxTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTempMax();
        maxTemp = weatherService.convertToCelsius(maxTemp);
        String maxTempString = weatherService.roundOfTwoDecimal(maxTemp);

        double minTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getTempMin();
        minTemp = weatherService.convertToCelsius(minTemp);
        String minTempString = weatherService.roundOfTwoDecimal(minTemp);

        double feelsLikeTemp = Objects.requireNonNull(weatherForecast).getList().get(0).getMain().getFeelsLike();
        feelsLikeTemp = weatherService.convertToCelsius(feelsLikeTemp);
        String feelsLikeTempString = weatherService.roundOfTwoDecimal(feelsLikeTemp);

        model.addAttribute("forecast", weatherForecast);
        model.addAttribute("temp", tempString);
        model.addAttribute("maxTemp", maxTempString);
        model.addAttribute("minTemp", minTempString);
        model.addAttribute("feelsLikeTemp", feelsLikeTempString);

        return "bootstrap-template";
    }

}
