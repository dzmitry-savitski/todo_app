package by_.gsu.epamlab.todoapp.service;

import by_.gsu.epamlab.todoapp.entities.User;

/**
 * Represents service-layer for work with users.
 */
public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    User getUserByLogin(String login);

    User getUserByUuid(String uuid);

    int getIdByLogin(String login);

    void addNewUser(String login, String password, String email);
}
