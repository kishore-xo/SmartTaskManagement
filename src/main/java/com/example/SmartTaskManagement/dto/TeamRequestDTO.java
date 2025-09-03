package com.example.SmartTaskManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class TeamRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private Set<Long> usersId;
}
