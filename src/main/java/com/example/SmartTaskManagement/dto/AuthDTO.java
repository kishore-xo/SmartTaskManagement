package com.example.SmartTaskManagement.dto;

import com.example.SmartTaskManagement.model.Role;
import lombok.Data;

@Data
public class AuthDTO {
    private String username;
    private String password;
}
