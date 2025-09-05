package com.example.SmartTaskManagement.exception;

public class TaskNotFoundException extends RuntimeException {
    private String message;

    public TaskNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
