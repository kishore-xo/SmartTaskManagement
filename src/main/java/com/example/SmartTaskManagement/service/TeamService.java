package com.example.SmartTaskManagement.service;

import com.example.SmartTaskManagement.dto.TeamRequestDTO;
import com.example.SmartTaskManagement.dto.TeamResponseDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final UsersRepo usersRepo;
    private final TeamRepo teamRepo;
    private final TaskRepo taskRepo;

    private TeamResponseDTO mapToTeamDTO(Team team) {
        TeamResponseDTO dto = new TeamResponseDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());

        Set<String> username = team.getUsers()
                .stream().map(Users::getUsername)
                .collect(Collectors.toSet());
        dto.setUsernames(username);

        List<String> taskNames = team.getTasks()
                .stream().map(Task::getTitle)
                .toList();
        dto.setTaskNames(taskNames);

        return dto;
    }

    public List<TeamResponseDTO> getTeams() {
        return teamRepo.findAllWithUsersAndTasks()
                .stream()
                .map(this::mapToTeamDTO)
                .collect(Collectors.toList());
    }

    public TeamResponseDTO getTeamByName(String name) {
        Team team = teamRepo.findByNameWithUsersAndTasks(name)
                .orElseThrow(() -> new RuntimeException("Team not found with name " + name));
        return mapToTeamDTO(team);
    }

    public TeamResponseDTO createTeam(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());

        if (dto.getAssignedUser() != null && !dto.getAssignedUser().isEmpty()) {
            Set<Users> users = dto.getAssignedUser().stream()
                    .map(username -> usersRepo.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found with name " + username)))
                    .collect(Collectors.toSet());
            team.setUsers(users);
        }
        teamRepo.save(team);

        return mapToTeamDTO(team);
    }

    public TeamResponseDTO updateTeam(TeamRequestDTO dto) {
        Optional<Team> optionalTeam = teamRepo.findTeamByName(dto.getName());

        if (optionalTeam.isEmpty()) {
            throw new RuntimeException("Team with name " + dto.getName() + " not found");
        }
        Team team = optionalTeam.get();
        team.setName(dto.getName());
        if (dto.getAssignedUser() != null && !dto.getAssignedUser().isEmpty()) {
            Set<Users> users = dto.getAssignedUser().stream()
                    .map(username -> usersRepo.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found with name " + username)))
                    .collect(Collectors.toSet());
            team.setUsers(users);
        }
        teamRepo.save(team);
        return mapToTeamDTO(team);
    }

    public void deleteTeam(String name) {
        teamRepo.deleteByName(name);
    }
}
