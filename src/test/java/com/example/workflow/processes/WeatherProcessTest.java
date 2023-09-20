package com.example.workflow.processes;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

public class WeatherProcessTest {

    @RegisterExtension
    ProcessEngineExtension extension = ProcessEngineExtension.builder()
            .build();

    RuntimeService runtimeService = extension.getProcessEngine().getRuntimeService();
}
