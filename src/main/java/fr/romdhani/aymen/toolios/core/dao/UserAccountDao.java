package fr.romdhani.aymen.toolios.core.dao;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class UserAccountDao implements DaoInterface<UserAccount, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public UserAccountDao() {
        currentSession = getSession();
        currentTransaction = currentSession.getTransaction();
    }

    public Session getSessionwithTransaction() {
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
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        Address address = entity.getAddress();
        currentSession.save(address);
        currentTransaction.commit();
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.save(entity);
        currentTransaction.commit();
    }

    public void update(UserAccount entity) {
        getCurrentSession().update(entity);
    }

    public UserAccount findById(Long id) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        UserAccount userAccount = (UserAccount) currentSession.get(UserAccount.class, id);
        currentTransaction.commit();
        return userAccount;
    }

    public void delete(UserAccount entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.delete(entity);
        currentTransaction.commit();
    }

    @SuppressWarnings("unchecked")
    public List<UserAccount> findAll() {
        currentTransaction.begin();
        List<UserAccount> users = (List<UserAccount>) currentSession.createQuery("from UserAccount").list();
        currentTransaction.commit();
        return users;
    }

    public UserAccount findByLogin(String login) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.createCriteria(UserAccount.class).add(Restrictions.eq("id", 1l))
                .list();
        List<UserAccount> users = currentSession.createCriteria(UserAccount.class).add(Restrictions.eq("login", login))
                .list();
        currentTransaction.commit();
        if (users.size() > 1) return null;
        else return users.get(0);

    }

    public void deleteAll() {
        List<UserAccount> entityList = findAll();
        for (UserAccount entity : entityList) {
            delete(entity);
        }
    }
}