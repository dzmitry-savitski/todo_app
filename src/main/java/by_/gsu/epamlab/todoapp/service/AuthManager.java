package by_.gsu.epamlab.todoapp.service;

import by_.gsu.epamlab.todoapp.entities.User;
import by_.gsu.epamlab.todoapp.exceptions.LoginException;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages user in session.
 */
public interface AuthManager {
    /**
     * Returns user if logged in or null.
     */
    User getCurrentUser();

    /**
     * Tries to authenticate user with given login and password.
     * Generates new UUID and puts cookie info if 'rememberUser' flag is true.
     *
     * @throws LoginException in case of login errors.
     */
    void login(String login, String password, boolean rememberUser) throws LoginException;

    /**
     * Authenticate user by UUID cookie.
     *
     * @throws LoginException in case of login errors.
     */
    void loginFromServlet(String uuid, HttpServletRequest request) throws LoginException;

    /**
     * Destroys info about user in session variables and cleans UUID info if present.
     */
    void logout();
}
