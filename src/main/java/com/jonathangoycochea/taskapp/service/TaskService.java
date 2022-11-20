package com.jonathangoycochea.taskapp.service;

import com.jonathangoycochea.taskapp.dto.TaskInDTO;
import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    public Task createTask(TaskInDTO taskInDTO);

    public Optional<Task> getTask(Long id);

    public List<Task> getAllTasks();

    public List<Task> getAllTasksByStatus(TaskStatus taskStatus);

    public void updateTaskAsCompleted(Long id);

    public void deleteTaskById(Long id);

    public void deleteAllTasks();

    public void updateTaskStatusAs(Long id, TaskStatus taskStatus);
}
