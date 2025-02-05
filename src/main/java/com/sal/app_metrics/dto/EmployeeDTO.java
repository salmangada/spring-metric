package com.sal.app_metrics.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private double salary;
}
