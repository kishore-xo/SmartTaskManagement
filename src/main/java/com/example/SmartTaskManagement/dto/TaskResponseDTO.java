package com.example.SmartTaskManagement.dto;

import com.example.SmartTaskManagement.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String priority;
    private LocalDate dueDate;
    private String username;
    private String teamName;
}
