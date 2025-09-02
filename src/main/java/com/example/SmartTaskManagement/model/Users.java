package com.example.SmartTaskManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Email
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedUser", orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Team> teams = new HashSet<>();
}
