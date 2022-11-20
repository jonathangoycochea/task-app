package com.jonathangoycochea.taskapp.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private List<String> message;

    public ErrorResponse() {
        this.timeStamp = LocalDateTime.now();
    }
}
