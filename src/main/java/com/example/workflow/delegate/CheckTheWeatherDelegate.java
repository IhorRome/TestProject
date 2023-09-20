package com.example.workflow.delegate;

import com.example.workflow.service.WeatherService;
import com.example.workflow.util.CamundaFields;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckTheWeatherDelegate implements JavaDelegate {

    private final WeatherService weatherService;

    public CheckTheWeatherDelegate(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable(CamundaFields.WEATHER, weatherService.determineWeather());
    }
}
