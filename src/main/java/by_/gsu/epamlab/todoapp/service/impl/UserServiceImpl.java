package by_.gsu.epamlab.todoapp.service.impl;


import by_.gsu.epamlab.todoapp.db.UserDAO;
import by_.gsu.epamlab.todoapp.entities.User;
import by_.gsu.epamlab.todoapp.enums.Role;
import by_.gsu.epamlab.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User getUserByUuid(String uuid) {
        return userDAO.getUserByUuid(uuid);
    }

    @Override
    public int getIdByLogin(String login) {
        User user = userDAO.getUserByLogin(login);
        return user.getId();
    }

    @Override
    public void addNewUser(String login, String password, String email) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(Role.USER);

        userDAO.addUser(user);
    }
}
