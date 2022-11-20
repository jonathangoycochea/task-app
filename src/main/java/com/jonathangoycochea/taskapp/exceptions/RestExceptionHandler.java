package com.jonathangoycochea.taskapp.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException noSuchElementException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(Collections.singletonList(noSuchElementException.getMessage()));
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        List<String> messageList = new ArrayList<>(fieldErrorList.size());
        for (FieldError fieldError : fieldErrorList) {
            messageList.add(fieldError.getDefaultMessage());
        }
        errorResponse.setMessage(messageList);
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        return buildResponseEntity(errorResponse);

    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
