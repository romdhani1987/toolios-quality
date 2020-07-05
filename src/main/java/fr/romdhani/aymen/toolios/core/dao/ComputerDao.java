package fr.romdhani.aymen.toolios.core.dao;


import fr.romdhani.aymen.toolios.core.orm.Computer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class ComputerDao implements DaoInterface<Computer, Long> {

    private Session currentSession;
    private Transaction currentTransaction;

    public ComputerDao() {
    }


    public Session getSessionwithTransaction() {
        currentSession = getSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void commitTransaction() {
        currentTransaction.commit();
    }

    private Session getCurrentSession() {
        currentSession = getSession();
        return currentSession;
    }

    public void persist(Computer entity) {
        getCurrentSession().save(entity);
    }

    public void update(Computer entity) {
        getCurrentSession().update(entity);
    }

    public Computer findById(Long id) {
        Computer computer = (Computer) getCurrentSession().get(Computer.class, id);
        return computer;
    }

    public void delete(Computer entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Computer> findAll() {
        List<Computer> users = (List<Computer>) getCurrentSession().createQuery("from Computer").list();
        return users;
    }

    public void deleteAll() {
        List<Computer> entityList = findAll();
        for (Computer entity : entityList) {
            delete(entity);
        }
    }
}