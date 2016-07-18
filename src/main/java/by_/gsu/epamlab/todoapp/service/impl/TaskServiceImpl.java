package by_.gsu.epamlab.todoapp.service.impl;

import by_.gsu.epamlab.todoapp.db.TaskDAO;
import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.enums.Status;
import by_.gsu.epamlab.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDAO taskDAO;

    @Override
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }

    @Override
    public List<Task> getTaskListByUserId(int userId) {
        return taskDAO.getTaskListByUserId(userId);
    }

    @Override
    public void updateAllTasks(Collection<Task> tasks) {
        taskDAO.updateAllTasks(tasks);
    }

    @Override
    public void addTask(int userId, String title, Date date) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(title);
        task.setDate(date);
        task.setStatus(Status.IDEA);
        task.setCollapsed(false);
        task.setDashboardOrder(99);
        taskDAO.addTask(task);
    }
}
