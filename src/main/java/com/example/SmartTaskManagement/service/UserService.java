package com.example.SmartTaskManagement.service;

import com.example.SmartTaskManagement.dto.UserDTO;
import com.example.SmartTaskManagement.dto.UserResponseDTO;
import com.example.SmartTaskManagement.exception.UserNotFoundException;
import com.example.SmartTaskManagement.model.Users;
import com.example.SmartTaskManagement.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;

    public List<UserResponseDTO> getUsers() {
        return usersRepo.findAll().stream()
                .map(users -> new UserResponseDTO(
                        users.getId(),
                        users.getUsername(),
                        users.getEmail(),
                        users.getRole()))
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        Users users = usersRepo.findById(id).orElse(null);
        assert users != null;
        return new UserResponseDTO(users.getId(),
                users.getUsername(),
                users.getEmail(),
                users.getRole());
    }

    public UserResponseDTO createUser(UserDTO dto) {
        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setEmail(dto.getEmail());
        users.setRole(dto.getRole());
        users.setTasks(new HashSet<>());
        users.setTeams(new HashSet<>());
        usersRepo.save(users);
        return new UserResponseDTO(users.getId(), users.getUsername(), users.getEmail(), users.getRole());
    }

    public UserResponseDTO updateUser(Long id, UserDTO dto) {
        Optional<Users> users = usersRepo.findById(id);
        if (users.isPresent()) {
            Users existUser = users.get();
            existUser.setUsername(dto.getUsername());
            existUser.setPassword(dto.getPassword());
            existUser.setEmail(dto.getEmail());
            existUser.setRole(dto.getRole());
            usersRepo.save(existUser);
            return new UserResponseDTO(existUser.getId(),
                    existUser.getUsername(),
                    existUser.getEmail(),
                    existUser.getRole());
        }
        throw new UserNotFoundException("User with id " + id + " is Not Found");
    }

    public void deleteUser(Long id) {
        usersRepo.deleteById(id);
    }
}
