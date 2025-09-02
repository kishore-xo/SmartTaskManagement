package com.example.SmartTaskManagement.exception;

import org.springframework.context.annotation.Bean;

public class TaskNotFoundException extends RuntimeException {
    private String message;

    public TaskNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
