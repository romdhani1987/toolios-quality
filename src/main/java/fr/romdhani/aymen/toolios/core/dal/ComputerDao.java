package fr.romdhani.aymen.toolios.core.dal;


import fr.romdhani.aymen.toolios.utils.HibernateUtil;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class ComputerDao implements DaoInterface<Computer, String> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ComputerDao() {
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

    public void persist(Computer entity) {
        getCurrentSession().save(entity);
    }

    public void update(Computer entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Computer findById(String id) {
        Computer computer = (Computer) getCurrentSession().get(Computer.class, id);
        return computer;
    }

    public void delete(Computer entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Computer> findAll() {
        List<Computer> computers = (List<Computer>) getCurrentSession().createQuery("from Computer").list();
        return computers;
    }

    @Override
    public void deleteAll() {
        List<Computer> entityList = findAll();
        for (Computer entity : entityList) {
            delete(entity);
        }
    }
}