package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory sessionFactory = util.getSessionFactory();
    Session session = null;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            String sql = "CREATE TABLE Users (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age TINYINT(3), PRIMARY KEY (id));";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS Users;";

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(new User(name, lastName, age));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            session.delete(session.get(User.class, id));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List getAllUsers() {

        try {
            session = sessionFactory.openSession();
        session.beginTransaction();
            session.getTransaction().commit();

            return (session.createQuery("FROM User").list());


        } catch (HibernateException e) {
            e.printStackTrace();
            assert session.beginTransaction() != null;
            session.beginTransaction().rollback();
            return null;
        }
    }


    @Override
    public void cleanUsersTable() {

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            session.createSQLQuery("truncate table users").addEntity(User.class).executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}