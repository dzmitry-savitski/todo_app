package by_.gsu.epamlab.todoapp.db.impl;

import by_.gsu.epamlab.todoapp.db.TaskDAO;
import by_.gsu.epamlab.todoapp.entities.Task;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class HibernateTaskDAO implements TaskDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTask(Task task) {
        sessionFactory.
                getCurrentSession().
                save(task);
    }

    @Override
    public void updateTask(Task task) {
        sessionFactory.
                getCurrentSession().
                update(task);
    }

    @Override
    public void deleteTask(Task task) {
        final Session session =
                sessionFactory.getCurrentSession();
        session.delete(task);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> getTaskListByUserId(int userId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Task.class);
        criteria.add(Restrictions.eq("userId", userId))
                .addOrder(Order.asc("dashboardOrder"));
        return criteria.list();
    }

    @Override
    public void updateAllTasks(Collection<Task> tasks) {
        final Session session = sessionFactory.
                getCurrentSession();
        for (Task task : tasks) {
            session.update(task);
        }
    }

    @Override
    public byte[] getImage(int userId, int taskId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Task.class);
        criteria.add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("id", taskId))
                .setProjection(Projections.property("image"));
        return (byte[]) criteria.uniqueResult();
    }
}
