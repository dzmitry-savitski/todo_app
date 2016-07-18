package by_.gsu.epamlab.todoapp.views;

import by_.gsu.epamlab.todoapp.service.TaskService;
import by_.gsu.epamlab.todoapp.service.UserService;
import by_.gsu.epamlab.todoapp.utils.Constants;
import by_.gsu.epamlab.todoapp.utils.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * Holds values and listeners for new task form.
 */
@ManagedBean
@RequestScoped
public class NewTaskView {
    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    @ManagedProperty(value = "#{taskService}")
    private TaskService taskService;

    private String title;
    private Date date;

    public NewTaskView() {
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String addTask() {
        int userId = userService.getIdByLogin(FacesUtils.getRequest().getRemoteUser());
        taskService.addTask(userId, title, date);

        clearFields();

        return "pages";
    }

    public void validateTitle(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if ((Constants.TITLE_LENGTH_MIN > value.toString().length()) ||
                (value.toString().length() > Constants.TITLE_LENGTH_MAX)) {
            FacesMessage message =
                    FacesUtils.createFacesMessage("title_length_error", FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    private void clearFields() {
        title = null;
        date = null;
    }
}
