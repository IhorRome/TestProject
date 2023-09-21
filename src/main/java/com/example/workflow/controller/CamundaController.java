package com.example.workflow.controller;

import com.example.workflow.service.CamundaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runtime")
public class CamundaController {

    private final CamundaService camundaService;

    public CamundaController(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    @GetMapping("/complete-tasks/{key}")
    public ResponseEntity<?> completeTasks(@PathVariable String key) {
        camundaService.completeTasks(key);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/start/{key}")
    public ResponseEntity<?> startProcess(@PathVariable String key) {
        camundaService.startProcessInstanceByKey(key);
        return ResponseEntity.noContent().build();
    }

}
