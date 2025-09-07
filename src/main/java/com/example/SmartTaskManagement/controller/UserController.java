package com.example.SmartTaskManagement.controller;

import com.example.SmartTaskManagement.dto.UserRequestDTO;
import com.example.SmartTaskManagement.dto.UserResponseDTO;
import com.example.SmartTaskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('TASK_READ')")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TASK_WRITE')")
    public UserResponseDTO updateUser(@RequestBody UserRequestDTO dto, @PathVariable Long id) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TASK_DELETE')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
