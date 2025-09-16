package com.example.studentsapi.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
private String message;
private Boolean success;
private LocalDateTime timestamp;
private List <String> details;
public ErrorResponse () {
    super();
}
public ErrorResponse(String message, List <String> details) {
    super();
    this.message = message;
    this.details = details;
    this.success = false;
    this.timestamp = LocalDateTime.now();
}

}
