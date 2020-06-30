package fr.romdhani.aymen.toolios.core.dal;


import fr.romdhani.aymen.toolios.utils.HibernateUtil;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class UserAccountDao implements DaoInterface<UserAccount, String> {

    private Session currentSession;

    private Transaction currentTransaction;

    public UserAccountDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(UserAccount entity) {
        getCurrentSession().save(entity);
    }

    public void update(UserAccount entity) {
        getCurrentSession().update(entity);
    }

    public UserAccount findById(String id) {
        UserAccount userAccount = (UserAccount) getCurrentSession().get(UserAccount.class, id);
        return userAccount;
    }

    public void delete(UserAccount entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<UserAccount> findAll() {
        List<UserAccount> users = (List<UserAccount>) getCurrentSession().createQuery("from UserAccount").list();
        return users;
    }

    public void deleteAll() {
        List<UserAccount> entityList = findAll();
        for (UserAccount entity : entityList) {
            delete(entity);
        }
    }
}