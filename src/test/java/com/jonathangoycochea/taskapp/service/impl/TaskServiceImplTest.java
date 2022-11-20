package com.jonathangoycochea.taskapp.service.impl;

import com.jonathangoycochea.taskapp.dto.TaskInDTO;
import com.jonathangoycochea.taskapp.mapper.TaskInDTOToTask;
import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import com.jonathangoycochea.taskapp.persistence.repository.TaskRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskInDTOToTask taskInDTOToTask;

    @Test
    void Task_is_created() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setId(1L);
        expectedTask.setTitle("Title example");
        expectedTask.setDescription("Description example");
        expectedTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        expectedTask.setCompleted(false);
        expectedTask.setCreationDate(LocalDateTime.now());
        expectedTask.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);
        when(taskInDTOToTask.map(any(TaskInDTO.class))).thenReturn(expectedTask);

        TaskInDTO taskInDTO = new TaskInDTO();
        taskInDTO.setDescription("Description example");
        taskInDTO.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));
        taskInDTO.setTitle("Title example");

        // When
        Task actualTask = taskService.createTask(taskInDTO);

        // Then
        assertSame(expectedTask, actualTask);
        assertEquals(expectedTask.getDescription(), actualTask.getDescription());
        assertEquals(expectedTask.getTitle(), actualTask.getTitle());
        verify(taskRepository).save(any(Task.class));
        verify(taskInDTOToTask).map(any(TaskInDTO.class));
    }

    @Test
    void Task_under_a_given_id_is_returned() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setId(1L);
        expectedTask.setTitle("Title example");
        expectedTask.setDescription("Description example");
        expectedTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        expectedTask.setCompleted(false);
        expectedTask.setCreationDate(LocalDateTime.now());
        expectedTask.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(expectedTask));

        // When
        Optional<Task> actualTaskOptional = taskService.getTask(anyLong());
        Task actualTask = actualTaskOptional.get();

        // Then
        assertEquals(false, actualTask.isCompleted());
        assertEquals("Description example", actualTask.getDescription());
        assertEquals(expectedTask, actualTask);
        verify(taskRepository).findById(anyLong());
    }

    @Test
    void All_tasks_are_returned() {
        // Given
        Task expectedTask1 = new Task();
        expectedTask1.setId(1L);
        expectedTask1.setTitle("Title example 1");
        expectedTask1.setDescription("Description example 1");
        expectedTask1.setTaskStatus(TaskStatus.IN_PROGRESS);
        expectedTask1.setCompleted(false);
        expectedTask1.setCreationDate(LocalDateTime.now());
        expectedTask1.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        Task expectedTask2 = new Task();
        expectedTask2.setId(2L);
        expectedTask2.setTitle("Title example 2");
        expectedTask2.setDescription("Description example 2");
        expectedTask2.setTaskStatus(TaskStatus.IN_PROGRESS);
        expectedTask2.setCompleted(false);
        expectedTask2.setCreationDate(LocalDateTime.now());
        expectedTask2.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        List<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(expectedTask1);
        expectedTaskList.add(expectedTask2);
        when(taskRepository.findAll()).thenReturn(expectedTaskList);

        // When
        List<Task> actualTaskList = taskService.getAllTasks();

        // Then
        assertEquals("Title example 1", actualTaskList.get(0).getTitle());
        assertEquals("Title example 2", actualTaskList.get(1).getTitle());
        assertEquals(2, actualTaskList.size());
        assertEquals(expectedTaskList, actualTaskList);
        verify(taskRepository).findAll();
    }

    @Test
    void All_tasks_under_a_given_status_are_returned() {
        // Given
        Task expectedTask1 = new Task();
        expectedTask1.setId(1L);
        expectedTask1.setTitle("Title example 1");
        expectedTask1.setDescription("Description example 1");
        expectedTask1.setTaskStatus(TaskStatus.LATE);
        expectedTask1.setCompleted(false);
        expectedTask1.setCreationDate(LocalDateTime.now());
        expectedTask1.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        Task expectedTask2 = new Task();
        expectedTask2.setId(2L);
        expectedTask2.setTitle("Title example 2");
        expectedTask2.setDescription("Description example 2");
        expectedTask2.setTaskStatus(TaskStatus.LATE);
        expectedTask2.setCompleted(false);
        expectedTask2.setCreationDate(LocalDateTime.now());
        expectedTask2.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        List<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(expectedTask1);
        expectedTaskList.add(expectedTask2);

        when(taskRepository.findAllByTaskStatus(any(TaskStatus.class))).thenReturn(expectedTaskList);

        // When
        List<Task> actualTaskList = taskService.getAllTasksByStatus(TaskStatus.LATE);

        // Then
        assertEquals("Title example 1", actualTaskList.get(0).getTitle());
        assertEquals("Title example 2", actualTaskList.get(1).getTitle());
        assertEquals(2, actualTaskList.size());
        assertEquals(expectedTaskList, actualTaskList);
        verify(taskRepository).findAllByTaskStatus(any(TaskStatus.class));
    }

    @Test
    void Task_under_a_given_id_is_updated_as_completed() {
        // Given
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Title example");
        task.setDescription("Description example");
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setCompleted(false);
        task.setCreationDate(LocalDateTime.now());
        task.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        // When
        taskService.updateTaskAsCompleted(task.getId());

        // Then
        verify(taskRepository).updateTaskAsCompleted(task.getId());
    }

    @Test
    void Task_under_a_given_id_is_deleted() {
        // Given
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Title example");
        task.setDescription("Description example");
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setCompleted(false);
        task.setCreationDate(LocalDateTime.now());
        task.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        // When
        taskService.deleteTaskById(task.getId());

        // Then
        verify(taskRepository).deleteById(task.getId());
    }

    @Test
    void All_tasks_are_deleted() {
        // When
        taskService.deleteAllTasks();

        // Then
        verify(taskRepository).deleteAll();
    }

    @Test
    void Tasks_status_is_updated_as_the_given_status() {
        // Given
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Title example");
        task.setDescription("Description example");
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setCompleted(false);
        task.setCreationDate(LocalDateTime.now());
        task.setEstimatedCompletionDate(LocalDateTime.now().plusWeeks(1));

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        // When
        taskService.updateTaskStatusAs(task.getId(), TaskStatus.ON_HOLD);

        // Then
        verify(taskRepository).updateTaskStatusAs(anyLong(), any(TaskStatus.class));
    }
}