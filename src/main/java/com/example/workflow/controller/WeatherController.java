package com.example.workflow.controller;

import com.example.workflow.service.WeatherService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final RuntimeService runtimeService;

    private final WeatherService weatherService;

    public WeatherController(RuntimeService runtimeService,
                             WeatherService weatherService) {
        this.runtimeService = runtimeService;
        this.weatherService = weatherService;
    }

    @GetMapping("/check/{key}")
    public ResponseEntity<?> runWeatherProcess(@PathVariable String key) {
        weatherService.startProcessInstanceByKey(key);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/complete-tasks/{key}")
    public ResponseEntity<?> completeTasks(@PathVariable String key) {
        weatherService.completeTasks(key);
        return ResponseEntity.noContent().build();
    }
}
