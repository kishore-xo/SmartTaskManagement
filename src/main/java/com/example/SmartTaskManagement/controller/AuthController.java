package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.AuthDTO;
import com.example.SmartTaskManagement.dto.UserRequestDTO;
import com.example.SmartTaskManagement.dto.UserResponseDTO;
import com.example.SmartTaskManagement.model.Role;
import com.example.SmartTaskManagement.service.UserService;
import com.example.SmartTaskManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        return jwtUtil.generateToken(authDTO.getUsername());
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO){
        userRequestDTO.setRole(Role.USER);
        return userService.createUser(userRequestDTO);
    }

}
