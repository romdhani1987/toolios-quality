package fr.romdhani.aymen.toolios.controller.utils;

import fr.romdhani.aymen.toolios.core.orm.UserFunction;
import fr.romdhani.aymen.toolios.core.orm.UserRoles;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.execQuery;
import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

/**
 * This class used to populate the database in the first time
 */
public class DatabaseInitializer {
    private static class DatabaseInitializerHolder {
        public static final DatabaseInitializer instance = new DatabaseInitializer();
    }

    public static DatabaseInitializer getInstance() {
        return DatabaseInitializerHolder.instance;
    }

    private DatabaseInitializer() {
    }

    /**
     * Populate the roles table
     */
    public  void populateRoles() {
        System.out.println("INFO- Start to table user roles...");
        UserRoles userRoles = new UserRoles();
        userRoles.setName(UserRoles.UserRole.USER.getRole());
        Transaction tr = getSession().getTransaction();
        tr.begin();
        getSession().save(userRoles);
        tr.commit();
    }

    /**
     * Populate the table function
     */
    public  void populateFunction() {
        System.out.println("INFO- Start to populate function table ...");
        UserFunction function = new UserFunction();
        function.setName("Software engineer");
        execQuery(function);
    }

    /**
     * Populate groups
     */
    public synchronized void populateGroup() {

    }
}
