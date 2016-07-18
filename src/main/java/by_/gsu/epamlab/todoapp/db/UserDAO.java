package by_.gsu.epamlab.todoapp.db;

import by_.gsu.epamlab.todoapp.entities.User;

public interface UserDAO {
    void addUser(User user);

    void updateUser(User user);

    User getUserByLogin(String login);

    User getUserByUuid(String uuid);
}
