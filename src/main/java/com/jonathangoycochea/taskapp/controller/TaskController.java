package com.jonathangoycochea.taskapp.controller;

import com.jonathangoycochea.taskapp.dto.TaskInDTO;
import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import com.jonathangoycochea.taskapp.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation("Create task")
    @PostMapping
    public Task createTask(@Valid @RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @ApiOperation("Get all tasks")
    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    @ApiOperation("Get task")
    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable("id") Long id) {
        return this.taskService.getTask(id);
    }

    @ApiOperation("Get all tasks by status")
    @GetMapping("/status/{status}")
    public List<Task> getAllTasksByStatus(@PathVariable("status") TaskStatus taskStatus) {
        return this.taskService.getAllTasksByStatus(taskStatus);
    }

    @ApiOperation("Update task as completed")
    @PatchMapping("/update-as-completed/{id}")
    public ResponseEntity<Void> updateTaskAsCompleted(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsCompleted(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id) {
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Update task status")
    @PatchMapping("/update/{id}/status-as/{status}/")
    public ResponseEntity<Void> updateTaskStatusAs(@PathVariable("id") Long id, @PathVariable("status") TaskStatus taskStatus) {
        this.taskService.updateTaskStatusAs(id, taskStatus);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Delete all tasks")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks() {
        this.taskService.deleteAllTasks();
        return ResponseEntity.noContent().build();
    }
}
