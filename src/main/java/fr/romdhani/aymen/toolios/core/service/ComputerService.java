package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.ComputerDao;
import fr.romdhani.aymen.toolios.core.orm.Computer;

import java.util.List;


public class ComputerService {

    private static ComputerDao computerDao;

    public ComputerService() {
        computerDao = new ComputerDao();
    }

    public boolean persist(Computer entity) {
        try {
            computerDao.persist(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }


    public void update(Computer entity) {
        computerDao.update(entity);
    }

    public Computer findById(Long id) {
        Computer computer = computerDao.findById(id);
        return computer;
    }

    public void delete(Long id) {
        Computer computer = computerDao.findById(id);
        System.out.println("found computer: " + computer.toString());
        computerDao.delete(computer);
    }

    public List<Computer> findAll() {
        List<Computer> computers = computerDao.findAll();
        return computers;
    }

    public void deleteAll() {
        computerDao.deleteAll();
    }

    public ComputerDao computerDao() {
        return computerDao;
    }
}