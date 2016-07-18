package by_.gsu.epamlab.todoapp.db.impl;


import by_.gsu.epamlab.todoapp.db.UserDAO;
import by_.gsu.epamlab.todoapp.entities.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HibernateUserDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.
                getCurrentSession().
                save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.
                getCurrentSession().
                update(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User getUserByUuid(String uuid) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        return (User) criteria.uniqueResult();
    }
}
