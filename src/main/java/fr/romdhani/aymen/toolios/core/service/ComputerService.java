package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dal.ComputerDao;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import java.util.List;

public class ComputerService {

    private static ComputerDao computerDao;

    public ComputerService() {
        computerDao = new ComputerDao();
    }

    public void persist(Computer entity) {
        computerDao.openCurrentSessionwithTransaction();
        computerDao.persist(entity);
        computerDao.closeCurrentSessionwithTransaction();
    }

    public void update(Computer entity) {
        computerDao.openCurrentSessionwithTransaction();
        computerDao.update(entity);
        computerDao.closeCurrentSessionwithTransaction();
    }

    public Computer findById(String id) {
        computerDao.openCurrentSession();
        Computer computer = computerDao.findById(id);
        computerDao.closeCurrentSession();
        return computer;
    }

    public void delete(String id) {
        computerDao.openCurrentSessionwithTransaction();
        Computer computer = computerDao.findById(id);
        computerDao.delete(computer);
        computerDao.closeCurrentSessionwithTransaction();
    }

    public List<Computer> findAll() {
        computerDao.openCurrentSession();
        List<Computer> computers = computerDao.findAll();
        computerDao.closeCurrentSession();
        return computers;
    }

    public void deleteAll() {
        computerDao.openCurrentSessionwithTransaction();
        computerDao.deleteAll();
        computerDao.closeCurrentSessionwithTransaction();
    }

    public ComputerDao computerDao () {
        return computerDao;
    }
}