package by_.gsu.epamlab.todoapp.views;

import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.enums.Status;
import by_.gsu.epamlab.todoapp.service.TaskService;
import by_.gsu.epamlab.todoapp.utils.Constants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;

/**
 * Holds values and listeners for task panel.
 */
@ManagedBean
@ViewScoped
public class TaskPanelView implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    public TaskPanelView() {
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void updateTask(Task task) {
        taskService.updateTask(task);
    }

    public String deleteTask(Task task) {
        taskService.deleteTask(task);
        return "pages";
    }

    public String getColorForTask(Task task) {
        if ((task.getDate() == null) || (task.getStatus() == Status.DONE)) {
            return Constants.DEFAULT_TASK_COLOR;
        }

        final Date current = new Date();
        long diff = task.getDate().getTime() - current.getTime();

        double part = diff / (double) Constants.DATE_SEGMENT_FOR_GRADIENT;

        if (part > 1) {
            part = 1;
        } else if (part < 0) {
            part = 0;
        }

        final int HUE_FOR_DATE = (int) (Constants.DATE_GRADIENT_HUE_START +
                (Constants.DATE_GRADIENT_HUE_END - Constants.DATE_GRADIENT_HUE_START) * part);

        return String.format("hsl(%d, %d%%, %d%%)",
                HUE_FOR_DATE,
                Constants.DATE_GRADIENT_SATURATION,
                Constants.DATE_GRADIENT_BRIGHTNESS);

    }

    public void deleteDate(Task task) {
        task.setDate(null);
        taskService.updateTask(task);
    }
}
