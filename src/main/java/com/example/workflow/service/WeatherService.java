package com.example.workflow.service;

import java.util.List;
import java.util.Random;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
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

    public void startProcessInstanceByKey(String key) {
        runtimeService.startProcessInstanceByKey(key);
    }

    public void completeTasks(String key) {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(key)
                .list();

        tasks.forEach(t -> taskService.complete(t.getId()));
    }
}
