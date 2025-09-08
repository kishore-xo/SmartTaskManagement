package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.TeamRequestDTO;
import com.example.SmartTaskManagement.dto.TeamResponseDTO;
import com.example.SmartTaskManagement.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TASK_READ')")
    public Page<TeamResponseDTO> getTeams(@PageableDefault(size = 3, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return teamService.getTeams(pageable);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TASK_READ')")
    public TeamResponseDTO getTeamByName(@PathVariable String name) {
        return teamService.getTeamByName(name);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO dto) {
        return teamService.createTeam(dto);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public TeamResponseDTO updateTeam(@RequestBody TeamRequestDTO dto) {
        return teamService.updateTeam(dto);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TASK_DELETE')")
    public String deleteTeam(@PathVariable String name) {
        teamService.deleteTeam(name);
        return "Team Successfully deleted: " + name;
    }
}
