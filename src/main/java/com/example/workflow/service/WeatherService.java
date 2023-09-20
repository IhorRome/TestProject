package com.example.workflow.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final Random random;

    public WeatherService() {
        this.random = new Random();
    }

    public Boolean determineWeather() {
        return random.nextBoolean();
    }
}
