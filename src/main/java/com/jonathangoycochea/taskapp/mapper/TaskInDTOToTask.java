package com.jonathangoycochea.taskapp.mapper;

import com.jonathangoycochea.taskapp.dto.TaskInDTO;
import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {
    @Override
    public Task map(TaskInDTO taskInDTO) {
        Task task = new Task();
        task.setTitle(taskInDTO.getTitle());
        task.setDescription(taskInDTO.getDescription());
        task.setEstimatedCompletionDate(taskInDTO.getEstimatedCompletionDate());
        task.setCreationDate(LocalDateTime.now());
        task.setCompleted(false);
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        return task;
    }
}
