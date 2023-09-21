package com.example.workflow.processes;

import com.example.workflow.delegate.CheckTheWeatherDelegate;
import com.example.workflow.service.WeatherService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = "bpmn/weather-check.bpmn")
public class WeatherProcessTest {

    private static final String PROCESS_DEFINITION_KEY = "Process_0sgd6dn";

    @RegisterExtension
    ProcessEngineExtension extension = ProcessEngineExtension.builder().build();

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    public WeatherProcessTest() {
        this.runtimeService = extension.getProcessEngine().getRuntimeService();
        this.taskService = extension.getProcessEngine().getTaskService();
    }

    @BeforeEach
    void setUp() {
        Mocks.register("checkTheWeatherDelegate",
                new CheckTheWeatherDelegate(new WeatherService(runtimeService, taskService)));
    }
    @Test
    void shouldHaveActiveProcessInstance() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        assertThat(processInstance).isStarted();
    }

    @Test
    void shouldStopOnUserTask() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        assertThat(processInstance).task();
    }

    @Test
    void shouldHaveVariableWeather() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        assertThat(processInstance).hasVariables("weather");
    }

    @Test
    void shouldEndAfterCompletingTask() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
                .singleResult();

        taskService.complete(task.getId());

        assertThat(processInstance).isEnded();
    }

    @AfterEach
    void tearDown() {
        Mocks.reset();
    }
}
