package com.jonathangoycochea.taskapp.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class TaskInDTO {

    @Size(max = 60, message = "Title must not be longer than 60 characters")
    @NotEmpty(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Estimated Completion Date is mandatory")
    @Future(message = "You must not use a past date for the Estimated Completion Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime estimatedCompletionDate;
}
