package com.manu.weather.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manu.weather.model.WeatherErrorResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class WeatherRestExceptionHandler {

    @ExceptionHandler
    public String handleException(HttpClientErrorException exception, Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherErrorResponse response = objectMapper.readValue(exception.getResponseBodyAsString(), WeatherErrorResponse.class);
        model.addAttribute("error", response.getMessage());
        model.addAttribute("isError",true);
        return "index";
    }

}
