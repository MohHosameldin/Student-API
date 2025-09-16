package com.example.studentsapi.exceptions;

public class DuplicationException extends RuntimeException {
public DuplicationException(String message) {
        super(message);
    }
public DuplicationException() {
        super();
    }
}
