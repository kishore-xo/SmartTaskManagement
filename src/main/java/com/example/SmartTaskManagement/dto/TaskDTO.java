package com.example.SmartTaskManagement.dto;

import com.example.SmartTaskManagement.model.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private TaskStatus status;
    private String priority;
    private LocalDate dueDate;
    private Long assignedUser;
    private Long assignedTeam;
}
