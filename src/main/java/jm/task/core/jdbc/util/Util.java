package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

        private static SessionFactory sessionFactory;
        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();

                    // Hibernate settings equivalent to hibernate.cfg.xml's properties
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "Smth1987?!");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                    settings.put(Environment.SHOW_SQL, "true");

                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                    settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                    configuration.setProperties(settings);

                    configuration.addAnnotatedClass(UserDaoHibernateImpl.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();

                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }


    private static String url = "jdbc:mysql://localhost:3306/mydbtest";
    private static String username = "root";
    private static String password = "Smth1987?!";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


