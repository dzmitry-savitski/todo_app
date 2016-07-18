package by_.gsu.epamlab.todoapp.views;

import by_.gsu.epamlab.todoapp.entities.SubTask;
import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.service.TaskService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Holds values and listeners for sub task.
 */
@ManagedBean
@ViewScoped
public class SubTaskView implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    private String newSubTask;

    public SubTaskView() {
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getNewSubTasks() {
        return newSubTask;
    }

    public void setNewSubTasks(String newSubTasks) {
        this.newSubTask = newSubTasks;
    }

    public void addSubTask(Task task) {
        SubTask subTask = new SubTask();
        subTask.setText(newSubTask);

        subTask.setTaskId(task.getId());

        task.getSubTasks().add(subTask);
        taskService.updateTask(task);
        newSubTask = null;
    }

    public void deleteSubTask(Task task, SubTask subTask) {
        task.getSubTasks().remove(subTask);
        taskService.updateTask(task);
    }

    public String getStyle(SubTask subTask) {
        if (subTask.isDone()) {
            return "line-through";
        } else {
            return "none";
        }
    }
}
