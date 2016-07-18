package by_.gsu.epamlab.todoapp.service.impl;

import by_.gsu.epamlab.todoapp.entities.User;
import by_.gsu.epamlab.todoapp.exceptions.LoginException;
import by_.gsu.epamlab.todoapp.service.AuthManager;
import by_.gsu.epamlab.todoapp.service.UserService;
import by_.gsu.epamlab.todoapp.utils.AppUtils;
import by_.gsu.epamlab.todoapp.utils.Constants;
import by_.gsu.epamlab.todoapp.utils.FacesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service("authManager")
@SessionScoped
public class AuthManagerImpl implements AuthManager {
    @Autowired
    UserService userService;

    private User currentUser;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void login(String login, String password, boolean rememberUser) throws LoginException {
        String md5Password = AppUtils.md5(password);
        try {
            realmLogout();
            realmLogin(login, md5Password);
            currentUser = userService.getUserByLogin(login);

            if (rememberUser) {
                saveNewUuid();
            }
        } catch (ServletException e) {
            throw new LoginException(e);
        }
    }

    @Override
    public void loginFromServlet(String uuid, HttpServletRequest request) throws LoginException {
        currentUser = userService.getUserByUuid(uuid);
        if (currentUser != null) {
            try {
                request.logout();
                request.login(currentUser.getLogin(), currentUser.getPassword());
            } catch (ServletException e) {
                throw new LoginException(e);
            }
        } else {
            final String message = "No user with uuid " + uuid;
            throw new LoginException(message);
        }
    }

    @Override
    public void logout() {
        destroyUuid();
        try {
            realmLogout();
        } catch (ServletException e) {
            FacesUtils.destroySession();
        }
    }

    private void realmLogout() throws ServletException {
        FacesUtils.getRequest().logout();
    }

    private void realmLogin(String login, String password) throws ServletException {
        FacesUtils.getRequest().login(login, password);
    }

    private void saveNewUuid() {
        String uuid = UUID.randomUUID().toString();
        currentUser.setUuid(uuid);
        userService.updateUser(currentUser);

        FacesUtils.setCookie(Constants.UUID, uuid);
    }

    private void destroyUuid() {
        currentUser.setUuid(null);
        userService.updateUser(currentUser);

        FacesUtils.setCookie(Constants.UUID, null);
    }


}
