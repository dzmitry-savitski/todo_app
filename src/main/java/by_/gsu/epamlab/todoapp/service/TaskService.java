package by_.gsu.epamlab.todoapp.service;

import by_.gsu.epamlab.todoapp.entities.Task;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Represents service-layer for work with tasks.
 */
public interface TaskService {
    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTaskListByUserId(int userId);

    void updateAllTasks(Collection<Task> tasks);

    void addTask(int userId, String title, Date date);
}
