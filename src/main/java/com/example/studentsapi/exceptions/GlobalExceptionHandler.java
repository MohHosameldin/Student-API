package com.example.studentsapi.exceptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(RecordNotFound.class)
public ResponseEntity<?> handleRecordNotFound(RecordNotFound ex){
ErrorResponse error=new ErrorResponse(ex.getMessage(),Arrays.asList(ex.getMessage()));
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
    
@ExceptionHandler(NullException.class) 
public ResponseEntity<?>handleNullException(NullException ex){
ErrorResponse error=new ErrorResponse(ex.getMessage(),Arrays.asList(ex.getMessage()));
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

}
@ExceptionHandler(DuplicationException.class)
public ResponseEntity<?>handleDuplicationException(DuplicationException ex){
    ErrorResponse error=new ErrorResponse(ex.getMessage(),Arrays.asList(ex.getMessage()));
return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> details = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String errorMessage = error.getDefaultMessage();
        details.add(errorMessage);
    });
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}



}

