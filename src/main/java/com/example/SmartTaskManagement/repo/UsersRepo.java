package com.example.SmartTaskManagement.repo;

import com.example.SmartTaskManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    @Query("SELECT u.username FROM Users u WHERE u.id = :id")
    String findUsernameById(Long id);
}
