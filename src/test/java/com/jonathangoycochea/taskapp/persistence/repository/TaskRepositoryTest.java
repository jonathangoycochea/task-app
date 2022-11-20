package com.jonathangoycochea.taskapp.persistence.repository;

import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void Task_under_a_given_id_is_updated_as_completed() {
        // Given
        Task task = new Task();
        task.setCompleted(false);
        final Long id = entityManager.persistAndGetId(task, Long.class);

        // When
        taskRepository.updateTaskAsCompleted(id);

        // Then
        assertTrue(entityManager.find(Task.class, id).isCompleted());
    }

    @Test
    void Task_status_is_updated_as_the_given_status() {
        // Given
        Task task = new Task();
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        final Long id = entityManager.persistAndGetId(task, Long.class);

        // When
        taskRepository.updateTaskStatusAs(id, TaskStatus.ON_HOLD);

        // Then
        assertEquals(TaskStatus.ON_HOLD, entityManager.find(Task.class, id).getTaskStatus());
    }

}
