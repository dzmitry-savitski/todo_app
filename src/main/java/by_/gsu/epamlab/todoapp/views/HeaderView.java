package by_.gsu.epamlab.todoapp.views;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Holds header values.
 */
@ManagedBean
@ViewScoped
public class HeaderView implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authManager.currentUser.login}")
    private String userLogin;
    private boolean loginRendered = true;

    public HeaderView() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isLoginRendered() {
        return loginRendered;
    }

    public void setLoginRendered(boolean loginRendered) {
        this.loginRendered = loginRendered;
    }

    public void switchRenderedState() {
        loginRendered = !loginRendered;
    }
}
