package com.example.workflow.service;

import java.util.Random;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final Random random;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    public WeatherService(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.random = new Random();
    }

    public Boolean determineWeather() {
        return random.nextBoolean();
    }

}
