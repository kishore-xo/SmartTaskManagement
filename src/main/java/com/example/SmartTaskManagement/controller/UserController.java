package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.UserDTO;
import com.example.SmartTaskManagement.dto.UserResponseDTO;
import com.example.SmartTaskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return users;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        UserResponseDTO users = userService.getUserById(id);
        return users;
    }

    @PostMapping()
    public UserResponseDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@RequestBody UserDTO dto, @PathVariable Long id) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
