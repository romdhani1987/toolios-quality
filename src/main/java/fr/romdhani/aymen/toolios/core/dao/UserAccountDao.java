package fr.romdhani.aymen.toolios.core.dao;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class UserAccountDao implements DaoInterface<UserAccount, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public UserAccountDao() {
    }

    public Session getSessionwithTransaction() {
        currentSession = getSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void commitTransaction() {
        currentTransaction.commit();
    }

    public Session getCurrentSession() {
        currentSession = getSession();
        return currentSession;
    }

    public void persist(UserAccount entity) {
        getCurrentSession().save(entity);
    }

    public void update(UserAccount entity) {
        getCurrentSession().update(entity);
    }

    public UserAccount findById(Long id) {
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