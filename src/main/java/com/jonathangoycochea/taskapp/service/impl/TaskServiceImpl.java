package com.jonathangoycochea.taskapp.service.impl;

import com.jonathangoycochea.taskapp.dto.TaskInDTO;
import com.jonathangoycochea.taskapp.exceptions.NoSuchElementException;
import com.jonathangoycochea.taskapp.mapper.TaskInDTOToTask;
import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import com.jonathangoycochea.taskapp.persistence.repository.TaskRepository;
import com.jonathangoycochea.taskapp.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask taskInDTOToTaskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskInDTOToTask taskInDTOToTaskMapper) {
        this.taskRepository = taskRepository;
        this.taskInDTOToTaskMapper = taskInDTOToTaskMapper;
    }

    @Override
    @Transactional
    public Task createTask(TaskInDTO taskInDTO) {
        Task task = taskInDTOToTaskMapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> getTask(Long id) {
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return taskOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasksByStatus(TaskStatus taskStatus) {
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Override
    @Transactional
    public void updateTaskAsCompleted(Long id) {
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.taskRepository.updateTaskAsCompleted(id);
    }

    @Override
    @Transactional
    public void deleteTaskById(Long id) {
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllTasks() {
        this.taskRepository.deleteAll();
    }

    @Override
    @Transactional
    public void updateTaskStatusAs(Long id, TaskStatus taskStatus) {
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.taskRepository.updateTaskStatusAs(id, taskStatus);
    }
}
