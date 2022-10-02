package com.example.demo.exception_handler;

import com.example.demo.exception.InvalidDataException;
import com.example.demo.responseDTOs.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global controller for exception handling
 */

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(InvalidDataException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO("Invalid request", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
