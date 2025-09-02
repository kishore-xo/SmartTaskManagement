package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.TaskDTO;
import com.example.SmartTaskManagement.model.Task;
import com.example.SmartTaskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<Task> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping()
    public Task createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
