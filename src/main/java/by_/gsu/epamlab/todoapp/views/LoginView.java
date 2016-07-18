package by_.gsu.epamlab.todoapp.views;

import by_.gsu.epamlab.todoapp.exceptions.LoginException;
import by_.gsu.epamlab.todoapp.service.AuthManager;
import by_.gsu.epamlab.todoapp.utils.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Holds values and listeners for login form.
 */
@ManagedBean
@RequestScoped
public class LoginView {

    @ManagedProperty(value = "#{authManager}")
    private AuthManager authManager;

    private String login;
    private String password;
    private boolean remember;

    public LoginView() {
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String login() {
        try {
            authManager.login(login, password, remember);
            return "main";
        } catch (LoginException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage message = FacesUtils.createFacesMessage("auth_error", FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("login_form", message);
            return "index";
        }
    }

    public String logout() {
        authManager.logout();
        return "/index.xhtml?faces-redirect=true";
    }
}
