package com.example.SmartTaskManagement.model;

import java.util.Set;

public enum Role {
    ADMIN(Set.of("TASK_WRITE","TASK_READ","TASK_DELETE")),
    USER(Set.of("TASK_READ"));

    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }
}
