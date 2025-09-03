package com.example.SmartTaskManagement.dto;

import com.example.SmartTaskManagement.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotBlank(message = "Priority is required")
    private String priority;

    @NotNull(message = "DueDate is required")
    private LocalDate dueDate;

    private Long assignedUser;

    private Long assignedTeam;
}
