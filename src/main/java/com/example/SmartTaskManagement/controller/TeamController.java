package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.TeamRequestDTO;
import com.example.SmartTaskManagement.dto.TeamResponseDTO;
import com.example.SmartTaskManagement.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TeamResponseDTO> getTeams(){
        return teamService.getTeams();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDTO getTeamByName(@PathVariable String name){
        return teamService.getTeamByName(name);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO dto){
        return teamService.createTeam(dto);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeamResponseDTO updateTeam(@RequestBody TeamRequestDTO dto){
        return teamService.updateTeam(dto);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeam(@PathVariable String name){
        teamService.deleteTeam(name);
        return "Team deleted Successfully";
    }
}
