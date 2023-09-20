package com.example.workflow.controller;

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

    public WeatherController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping("/check/{key}")
    public ResponseEntity<?> runWeatherProcess(@PathVariable String key) {
        runtimeService.startProcessInstanceByKey(key);
        return ResponseEntity.noContent().build();
    }
}
