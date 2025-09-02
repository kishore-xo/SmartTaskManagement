package com.example.SmartTaskManagement.service;

import com.example.SmartTaskManagement.dto.TaskDTO;
import com.example.SmartTaskManagement.exception.TaskNotFoundException;
import com.example.SmartTaskManagement.model.Task;
import com.example.SmartTaskManagement.model.Team;
import com.example.SmartTaskManagement.model.Users;
import com.example.SmartTaskManagement.repo.TaskRepo;
import com.example.SmartTaskManagement.repo.TeamRepo;
import com.example.SmartTaskManagement.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UsersRepo usersRepo;
    private final TeamRepo teamRepo;
    private final TaskRepo taskRepo;

    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
        return task;
    }

    public Task createTask(TaskDTO taskDTO) {
        Users users = usersRepo.findById(taskDTO.getAssignedUser()).orElse(null);
        Team team = teamRepo.findById(taskDTO.getAssignedTeam()).orElse(null);

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate());
        task.setAssignedUser(users);
        task.setAssignedTeam(team);

        return task;
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {
        Optional<Task> task = taskRepo.findById(id);

        if (task.isPresent()) {
            Task oldTask = task.get();
            oldTask.setTitle(taskDTO.getTitle());
            oldTask.setDescription(taskDTO.getDescription());
            oldTask.setStatus(taskDTO.getStatus());
            oldTask.setPriority(taskDTO.getPriority());
            oldTask.setDueDate(taskDTO.getDueDate());
            taskRepo.save(oldTask);
            return oldTask;
        }
        throw new TaskNotFoundException("Task with ID " + id + " Not Found");
    }
}
