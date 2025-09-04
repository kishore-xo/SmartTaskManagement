package com.example.SmartTaskManagement.service;

import com.example.SmartTaskManagement.dto.TaskRequestDTO;
import com.example.SmartTaskManagement.dto.TaskResponseDTO;
import com.example.SmartTaskManagement.exception.TaskNotFoundException;
import com.example.SmartTaskManagement.model.Task;
import com.example.SmartTaskManagement.model.Team;
import com.example.SmartTaskManagement.model.Users;
import com.example.SmartTaskManagement.repo.TaskRepo;
import com.example.SmartTaskManagement.repo.TeamRepo;
import com.example.SmartTaskManagement.repo.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UsersRepo usersRepo;
    private final TeamRepo teamRepo;
    private final TaskRepo taskRepo;

    private TaskResponseDTO mapToTaskDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setDueDate(task.getDueDate());

        if (task.getAssignedUser() != null) {
            taskResponseDTO.setAssignedUser(task.getAssignedUser().getUsername());
        } else taskResponseDTO.setAssignedUser(null);
        if (task.getAssignedTeam() != null) {
            taskResponseDTO.setAssignedTeam(task.getAssignedTeam().getName());
        } else taskResponseDTO.setAssignedTeam(null);
        return taskResponseDTO;
    }

    public List<TaskResponseDTO> getTasks() {
        return taskRepo.findAll()
                .stream()
                .map(this::mapToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("NO Task with id" + id + " is found"));
        return mapToTaskDTO(task);
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) {
        Users users = null;
        if (taskDTO.getAssignedUser() != null) {
            users = usersRepo.findByUsername(taskDTO.getAssignedUser())
                    .orElseThrow(() -> new RuntimeException("User not found with name " + taskDTO.getAssignedUser()));
        }

        Team team = null;
        if (taskDTO.getAssignedTeam() != null) {
            team = teamRepo.findTeamByName(taskDTO.getAssignedTeam())
                    .orElseThrow(() -> new RuntimeException("Team not found with name " + taskDTO.getAssignedTeam()));
        }

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate());
        task.setAssignedUser(users);
        if (team != null) {
            task.setAssignedTeam(team);
            team.getTasks().add(task);
        }
        Task createdTask = taskRepo.save(task);
        return mapToTaskDTO(createdTask);
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskDTO) {
        Optional<Task> optionalTask = taskRepo.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + id + " Not Found");
        }

        Task task = optionalTask.get();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate());

        if (taskDTO.getAssignedUser() != null) {
            Users users = usersRepo.findByUsername(taskDTO.getAssignedUser())
                    .orElseThrow(() -> new RuntimeException("User not found in id " + taskDTO.getAssignedUser()));
            task.setAssignedUser(users);
        }

        if (taskDTO.getAssignedTeam() != null) {
            Team team = teamRepo.findTeamByName(taskDTO.getAssignedTeam())
                    .orElseThrow(() -> new RuntimeException("Team not found in id " + taskDTO.getAssignedTeam()));
            task.setAssignedTeam(team);
            team.getTasks().add(task);
        }

        Task updatedTask = taskRepo.save(task);
        return mapToTaskDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}