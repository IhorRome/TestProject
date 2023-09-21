package com.example.workflow.delegate;

import com.example.workflow.util.CamundaFields;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class WhatToDoDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable(CamundaFields.TO_WATCH_MOVIE, true);
        execution.setVariable(CamundaFields.TO_HAVE_DRINK, true);
        execution.setVariable(CamundaFields.TO_HAVE_SNACK, false);
    }
}
