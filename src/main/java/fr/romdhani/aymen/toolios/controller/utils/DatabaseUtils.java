package fr.romdhani.aymen.toolios.controller.utils;

import fr.romdhani.aymen.toolios.core.orm.*;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

/**
 * This class used to populate the database in the first time
 */
public class DatabaseUtils {
    private static class DatabaseInitializerHolder {
        public static final DatabaseUtils instance = new DatabaseUtils();
    }

    public static DatabaseUtils getInstance() {
        return DatabaseInitializerHolder.instance;
    }

    private DatabaseUtils() {
    }

    /**
     * Populate the roles table
     */
    public void populateRoles() {
        System.out.println("INFO- Start to populate table user roles...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        UserRoles userRoles = new UserRoles();
        userRoles.setName(UserRoles.UserRole.USER.getRole());
        getSession().save(userRoles);
        tr.commit();
    }

    /**
     * Populate the table function
     */
    public void populateFunction() {
        System.out.println("INFO- Start to populate function table ...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        UserFunction function = new UserFunction();
        function.setName("Software engineer");
        getSession().save(function);
        tr.commit();
    }

    /**
     * Populate groups
     */
    public synchronized void populateCompany() {
        System.out.println("INFO- Start to populate Company table ...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        Company company = new Company();
        company.setName("RS2D");
        getSession().save(company);
        tr.commit();
    }

    /**
     * Populate groups
     */
    public synchronized void populateGroup() {

        System.out.println("INFO- Start to populate user group table ...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        Company company = new Company();
        company.setName("RS2D");
        getSession().save(company);

        UserGroup userGroup = new UserGroup();
        userGroup.setName("Software");
        userGroup.setCompany(company);
        getSession().save(userGroup);
        tr.commit();
    }

    public List<UserAccount> getUsers() {
        return (List<UserAccount>) getSession().createQuery("from UserAccount").list();
    }
}
