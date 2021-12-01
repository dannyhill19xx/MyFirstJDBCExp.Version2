package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        //UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();
        userDaoHibernateImpl.createUsersTable();
        //user.saveUser("Rick", "Sanchez", (byte) 50);
        //user.saveUser("Morty", "Sanchez", (byte) 14);
        //user.saveUser("Ricky", "Sanchez", (byte) 22);
        //user.saveUser("Rickitacky", "Sanchez", (byte) 36);
        //user.getAllUsers();
        //user.cleanUsersTable();
        //user.dropUsersTable();

    }
}
