package fr.romdhani.aymen.toolios;

import java.util.List;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.dal.ExecutionContext;
import org.hibernate.Session;

import org.hibernate.Transaction;

/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class TooliosQuality {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        TooliosQuality application = new TooliosQuality();


        /*
         * Save few objects with hibernate
         */
        ExecutionContext executionContext = ExecutionContext.getInstance();
        executionContext.tryInTransaction(session -> {

            UserAccount userAccount = (UserAccount) session.createQuery(
                    "from UserAccount").list().get(0);

            Computer computer = new Computer();
            computer.setName("Goujon");
            computer.setOs("Unix");
            computer.setRam("8 Gbyte");
            computer.setShifting(false);
            computer.setUserAccount(userAccount);

            session.save(userAccount);
            session.save(computer);

        });


        /*
         * Retrieve all saved objects
         */
        List<UserAccount> userss = application.getAllUserAccounts();
        System.out.println("List of all persisted users >>>");
        for (UserAccount user : userss) {
            System.out.println("Persisted UserAccount: " + user.toString());
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
