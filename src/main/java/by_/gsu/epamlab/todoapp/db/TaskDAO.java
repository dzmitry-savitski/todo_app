package by_.gsu.epamlab.todoapp.db;

import by_.gsu.epamlab.todoapp.entities.Task;

import java.util.Collection;
import java.util.List;

public interface TaskDAO {
    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTaskListByUserId(int userId);

    void updateAllTasks(Collection<Task> tasks);

    byte[] getImage(int userId, int taskId);
}
