package com.jonathangoycochea.taskapp.persistence.repository;

import com.jonathangoycochea.taskapp.persistence.entity.Task;
import com.jonathangoycochea.taskapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE TASK SET COMPLETED=true WHERE ID=:id", nativeQuery = true)
    void updateTaskAsCompleted(@Param("id") Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE TASK SET TASK_STATUS= :#{#status.name()} WHERE ID = :id", nativeQuery = true)
    void updateTaskStatusAs(@Param(value = "id") Long id, @Param(value = "status") TaskStatus status);
}
