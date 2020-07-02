package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.ComputerDao;
import fr.romdhani.aymen.toolios.core.orm.Computer;

import java.util.List;


public class ComputerService {

    private static ComputerDao computerDao;

    public ComputerService() {
        computerDao = new ComputerDao();
    }

    public void persist(Computer entity) {
        computerDao.getSessionwithTransaction();
        computerDao.persist(entity);
        computerDao.commitTransaction();
    }

    public void update(Computer entity) {
        computerDao.getSessionwithTransaction();
        computerDao.update(entity);
        computerDao.commitTransaction();
    }

    public Computer findById(String id) {
        computerDao.getSessionwithTransaction();
        Computer computer = computerDao.findById(id);
        computerDao.commitTransaction();
        return computer;
    }

    public void delete(String id) {
        computerDao.getSessionwithTransaction();
        Computer computer = computerDao.findById(id);
        computerDao.delete(computer);
        computerDao.commitTransaction();
    }

    public List<Computer> findAll() {
        computerDao.getSessionwithTransaction();
        List<Computer> computers = computerDao.findAll();
        computerDao.commitTransaction();
        return computers;
    }

    public void deleteAll() {
        computerDao.getSessionwithTransaction();
        computerDao.deleteAll();
        computerDao.commitTransaction();
    }

    public ComputerDao computerDao() {
        return computerDao;
    }
}