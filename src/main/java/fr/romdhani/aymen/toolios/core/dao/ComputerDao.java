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

    /**
     * Persist an entity
     * @param entity
     */
    public void persist(Computer entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.save(entity);
        currentTransaction.commit();
    }

    public void update(Computer entity) {
        getCurrentSession().update(entity);
    }

    /**
     * find a computer
     *
     * @param id the id to search
     * @return
     */
    public Computer findById(Long id) {
        Computer computer = (Computer) getCurrentSession().get(Computer.class, id);
        return computer;
    }

    /**
     * Delete the computer from the database
     *
     * @param entity
     */
    public void delete(Computer entity) {
        currentTransaction = currentSession.getTransaction();
        currentTransaction.begin();
        currentSession.delete(entity);
        currentTransaction.commit();
    }

    @SuppressWarnings("unchecked")
    public List<Computer> findAll() {
        List<Computer> computers = (List<Computer>) getCurrentSession().createQuery("from Computer").list();
        return computers;
    }

    /**
     * Delete all rows
     */
    public void deleteAll() {
        List<Computer> entityList = findAll();
        for (Computer entity : entityList) {
            delete(entity);
        }
    }
}