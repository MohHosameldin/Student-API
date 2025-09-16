package com.example.studentsapi.exceptions;

public class RecordNotFound extends RuntimeException {
public RecordNotFound(String message) {
        super(message);
    }
public RecordNotFound() {
        super();
    }


}
