package com.sal.app_metrics.service;

import com.sal.app_metrics.dto.EmployeeDTO;
import com.sal.app_metrics.entity.Employee;
import com.sal.app_metrics.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }


    public Long save(EmployeeDTO emp) {

        return repo.save(
                Employee.builder()
                        .username(emp.getUsername())
                        .name(emp.getName())
                        .email(emp.getEmail())
                        .salary(emp.getSalary())
                        .build()
        ).getId();
    }

    public List<EmployeeDTO> getEmployee() {
        return repo.findAll().stream()
                .map(emp -> EmployeeDTO.builder()
                        .id(emp.getId())
                        .name(emp.getName())
                        .salary(emp.getSalary())
                        .username(emp.getUsername())
                        .email(emp.getEmail())
                        .build())
                .toList();
    }
}
