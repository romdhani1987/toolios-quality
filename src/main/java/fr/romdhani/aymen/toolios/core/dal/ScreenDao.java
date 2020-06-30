package fr.romdhani.aymen.toolios.core.dal;


import fr.romdhani.aymen.toolios.HibernateUtil;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class ScreenDao implements DaoInterface<Screen, String> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ScreenDao() {
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

    public void persist(Screen entity) {
        getCurrentSession().save(entity);
    }

    public void update(Screen entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Screen findById(String id) {
        Screen screen = (Screen) getCurrentSession().get(Computer.class, id);
        return screen;
    }

    public void delete(Screen entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Screen> findAll() {
        List<Screen> screens = (List<Screen>) getCurrentSession().createQuery("from Screen").list();
        return screens;
    }

    @Override
    public void deleteAll() {
        List<Screen> entityList = findAll();
        for (Screen entity : entityList) {
            delete(entity);
        }
    }
}