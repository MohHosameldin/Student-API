package com.example.studentsapi.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
private String message;
private Boolean success;
private LocalDateTime timestamp;
private List <String> details;
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
}
public Boolean getSuccess() {
    return success;
}
public void setSuccess(Boolean success) {
    this.success = success;
}
public LocalDateTime getTimestamp() {
    return timestamp;
}
public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
}
public List<String> getDetails() {
    return details;
}
public void setDetails(List<String> details) {
    this.details = details;
}
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
