package fr.romdhani.aymen.toolios.core.dao;


import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class ScreenDao implements DaoInterface<Screen, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ScreenDao() {
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

    public void persist(Screen entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.save(entity);
        currentTransaction.commit();
    }


    public void update(Screen entity) {
        getCurrentSession().update(entity);
    }

    public Screen findById(Long id) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        Screen screen = (Screen) currentSession.get(Screen.class, id);
        currentTransaction.commit();
        return screen;
    }

    public void delete(Screen entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.delete(entity);
        currentTransaction.commit();
    }

    @SuppressWarnings("unchecked")
    public List<Screen> findAll() {
        currentTransaction.begin();
        List<Screen> screens = (List<Screen>) currentSession.createQuery("from Screen").list();
        currentTransaction.commit();
        return screens;
    }

    public void deleteAll() {
        List<Screen> entityList = findAll();
        for (Screen entity : entityList) {
            delete(entity);
        }
    }
}