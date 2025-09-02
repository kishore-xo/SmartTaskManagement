package com.example.SmartTaskManagement.dto;

import com.example.SmartTaskManagement.model.Task;
import com.example.SmartTaskManagement.model.Users;
import lombok.Data;

import java.util.Set;

@Data
public class TeamDTO {
    private String name;
    private Set<Users> users;
    private Set<Task> tasks;
}
