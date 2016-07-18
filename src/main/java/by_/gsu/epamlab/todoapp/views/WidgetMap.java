package by_.gsu.epamlab.todoapp.views;


import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.entities.User;
import by_.gsu.epamlab.todoapp.service.AuthManager;
import by_.gsu.epamlab.todoapp.service.TaskService;
import by_.gsu.epamlab.todoapp.utils.Constants;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all tasks in widget map ordered by their position in columns.
 */
@ManagedBean
@ViewScoped
public class WidgetMap implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authManager}")
    private transient AuthManager authManager;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    private Map<String, Task> widgetMap;

    public WidgetMap() {
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public Map<String, Task> getWidgetMap() {
        return widgetMap;
    }

    @PostConstruct
    private void init() {
        widgetMap = new LinkedHashMap<>();
        User currentUser = authManager.getCurrentUser();
        final List<Task> taskList = taskService.getTaskListByUserId(currentUser.getId());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String widgetId = Constants.TASK_ID_PREFIX + i;
            widgetMap.put(widgetId, task);
        }
    }
}
