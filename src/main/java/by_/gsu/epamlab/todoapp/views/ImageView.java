package by_.gsu.epamlab.todoapp.views;

import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.service.TaskService;
import org.primefaces.event.FileUploadEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Holds image listeners.
 */
@ManagedBean
public class ImageView implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    public ImageView() {
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void upload(FileUploadEvent event) {
        final Task task = (Task) event.getComponent().getAttributes().get("task");
        task.setImage(event.getFile().getContents());
        taskService.updateTask(task);
    }

    public void deleteImage(Task task) {
        task.setImage(null);
        taskService.updateTask(task);
    }
}
