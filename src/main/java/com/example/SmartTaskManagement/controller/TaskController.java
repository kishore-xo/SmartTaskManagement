package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.TaskRequestDTO;
import com.example.SmartTaskManagement.dto.TaskResponseDTO;
import com.example.SmartTaskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<TaskResponseDTO> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping()
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@RequestBody TaskRequestDTO taskDTO, @PathVariable Long id) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
