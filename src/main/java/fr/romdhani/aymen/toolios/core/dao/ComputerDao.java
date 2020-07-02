package fr.romdhani.aymen.toolios.core.dao;


import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.HibernateUtil;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;


public class ComputerDao implements DaoInterface<Computer, String> {

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

    @Override
    public void persist(Computer entity) {

    }

    @Override
    public void update(Computer entity) {

    }

    public Computer findById(String id) {
        Computer computer = (Computer) getCurrentSession().get(Computer.class, id);
        return computer;
    }

    @Override
    public void delete(Computer entity) {

    }

    public void delete(UserAccount entity) {
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