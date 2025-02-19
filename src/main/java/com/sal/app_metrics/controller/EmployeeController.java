package com.sal.app_metrics.controller;

import com.sal.app_metrics.dto.EmployeeDTO;
import com.sal.app_metrics.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody EmployeeDTO employeeDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.save(employeeDTO));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeService.getEmployee());
    }
}
