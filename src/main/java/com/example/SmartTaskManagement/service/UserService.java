package com.example.SmartTaskManagement.service;

import com.example.SmartTaskManagement.dto.UserRequestDTO;
import com.example.SmartTaskManagement.dto.UserResponseDTO;
import com.example.SmartTaskManagement.model.Role;
import com.example.SmartTaskManagement.model.Users;
import com.example.SmartTaskManagement.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserResponseDTO mapToResponseDTO(Users users) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setEmail(users.getEmail());
        dto.setRole(users.getRole().name());
        return dto;
    }

    public List<UserResponseDTO> getUsers() {
        return usersRepo.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        Users users = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id" + id));
        return mapToResponseDTO(users);
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        users.setEmail(dto.getEmail());
        users.setRole(dto.getRole());

        Users saved = usersRepo.save(users);
        return mapToResponseDTO(saved);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        Optional<Users> optionalUsers = usersRepo.findById(id);

        if (optionalUsers.isEmpty()) {
            throw new RuntimeException("User with id " + id + " is not found");
        }

        Users users = optionalUsers.get();
        users.setUsername(dto.getUsername());
        users.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        users.setEmail(dto.getEmail());
        users.setRole(dto.getRole());

        Users updated = usersRepo.save(users);
        return mapToResponseDTO(updated);
    }

    public void deleteUser(Long id) {
        usersRepo.deleteById(id);
    }

}
