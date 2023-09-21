package com.example.workflow.service;

import java.util.List;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

@Service
public class CamundaService {

    private final TaskService taskService;

    private final RuntimeService runtimeService;

    public CamundaService(TaskService taskService,
                          RuntimeService runtimeService) {
        this.taskService = taskService;
        this.runtimeService = runtimeService;
    }

    public void completeTasks(String key) {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(key)
                .list();

        tasks.forEach(t -> taskService.complete(t.getId()));
    }

    public void startProcessInstanceByKey(String key) {
        runtimeService.startProcessInstanceByKey(key);
    }
}
