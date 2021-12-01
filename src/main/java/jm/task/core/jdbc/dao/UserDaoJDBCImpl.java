package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Statement statement;

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE users (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, " +
                    "lastName CHAR(30) NOT NULL, age MEDIUMINT NOT NULL, PRIMARY KEY (id));");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("drop table users");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
            //что такое autocommit, rollback,

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStmt;
            preparedStmt = connection.prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)");
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setByte(3, age);

            preparedStmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }

        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("delete from users where id = 1");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }

        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            //What is "result set"?
            ResultSet rs = statement.executeQuery("select id, name, lastName, age from users");
            while (rs.next()) {
                list.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("truncate table users");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }

        }
    }

}
