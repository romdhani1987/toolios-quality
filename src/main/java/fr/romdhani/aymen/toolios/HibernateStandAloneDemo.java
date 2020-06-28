package fr.romdhani.aymen.toolios;

import java.util.List;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.dal.ExecutionContext;
import org.hibernate.Session;

import fr.romdhani.aymen.toolios.core.orm.Student;
import org.hibernate.Transaction;

/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class HibernateStandAloneDemo {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        HibernateStandAloneDemo application = new HibernateStandAloneDemo();

        /*
         * Save few objects with hibernate
         */
        ExecutionContext executionContext = ExecutionContext.getInstance();
        executionContext.tryInTransaction(session -> {
            System.out.println("test");
            UserAccount userAccount = new UserAccount();
            userAccount.setF_name("aymen");
            userAccount.setL_name("romdhani");
            userAccount.setLogin("aromdhani");
            session.save(userAccount);
        });

        /*
         * Retrieve all saved objects
         */
        List<UserAccount> students = application.getAllUserAccounts();
        System.out.println("List of all persisted users >>>");
        for (UserAccount student : students) {
            System.out.println("Persisted UserAccount :" + student);
        }

        /*
         * Update an object
         */
        //application.updateUserAccount(userId4, "arom");

        /*
         * Deletes an object
         */
        //application.deleteUserAccount(userId2);

        /*
         * Retrieve all saved objects
         */
        List<UserAccount> remaingStudents = application.getAllUserAccounts();
        System.out.println("List of all remained persisted userAccounts >>>");
        for (UserAccount userAccount : remaingStudents) {
            System.out.println("Persisted UserAccount :" + userAccount);
        }
    }

    /**
     * This method saves an user  object in database
     */
    public int saveUserAccount(String firstName, String lastName, String login) {
        Transaction tx = null;
        Session session = null;
        try {
            // create session
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            UserAccount student = new UserAccount();
            student.setF_name(firstName);
            student.setL_name(lastName);
            student.setLogin(login);
            session.save(student);
            // do something
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception exp) {
            tx.rollback();
            // close session
            try {
                if (session != null && session.isOpen()) session.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            return -1;
        }
        return 1;
    }

    /**
     * This method returns list of all persisted userAccount objects/tuples from
     * database
     */
    public List<UserAccount> getAllUserAccounts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<UserAccount> users = (List<UserAccount>) session.createQuery(
                "from UserAccount").list();

        session.getTransaction().commit();
        session.close();
        return users;
    }

    /**
     * This method updates a specific UserAccount object
     */
    public void updateUserAccount(int id, String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserAccount userAccount = (UserAccount) session.get(UserAccount.class, id);
        userAccount.setLogin(login);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * This method deletes a specific UserAccount object
     */
    public void deleteUserAccount(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserAccount userAccount = (UserAccount) session.get(UserAccount.class, id);
        session.delete(userAccount);
        session.getTransaction().commit();
        session.close();
    }
}
