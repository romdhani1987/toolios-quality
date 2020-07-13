package fr.romdhani.aymen.toolios.core.dao;


import fr.romdhani.aymen.toolios.core.orm.License;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class LicenseDao implements DaoInterface<License, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public LicenseDao() {
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

    public void persist(License entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.save(entity);
        currentTransaction.commit();
    }


    public void update(License entity) {
        getCurrentSession().update(entity);
    }

    public License findById(Long id) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        License license = (License) currentSession.get(License.class, id);
        currentTransaction.commit();
        return license;
    }

    public void delete(License entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.delete(entity);
        currentTransaction.commit();
    }

    @SuppressWarnings("unchecked")
    public List<License> findAll() {
        currentTransaction.begin();
        List<License> licenses = (List<License>) currentSession.createQuery("from License").list();
        currentTransaction.commit();
        return licenses;
    }

    public void deleteAll() {
        List<License> entityList = findAll();
        for (License entity : entityList) {
            delete(entity);
        }
    }
}