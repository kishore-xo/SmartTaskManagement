package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.TaskRequestDTO;
import com.example.SmartTaskManagement.dto.TaskResponseDTO;
import com.example.SmartTaskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    @PreAuthorize("hasAuthority('TASK_READ')")
    public Page<TaskResponseDTO> getTasks(@PageableDefault(size = 3, sort = "dueDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return taskService.getTasks(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TASK_READ')")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public TaskResponseDTO updateTask(@RequestBody TaskRequestDTO taskDTO, @PathVariable Long id) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TASK_DELETE')")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
