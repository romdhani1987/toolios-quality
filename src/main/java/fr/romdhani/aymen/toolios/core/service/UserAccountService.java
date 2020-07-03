package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.UserAccountDao;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import java.util.List;


public class UserAccountService {

    private static UserAccountDao userAccountDao;

    public UserAccountService() {
        userAccountDao = new UserAccountDao();
    }

    public void persist(UserAccount entity) {
        userAccountDao.getSessionwithTransaction();
        userAccountDao.persist(entity);
        userAccountDao.commitTransaction();
    }

    public void update(UserAccount entity) {
        userAccountDao.getSessionwithTransaction();
        userAccountDao.update(entity);
        userAccountDao.commitTransaction();
    }

    public UserAccount findById(Long id) {
        userAccountDao.getSessionwithTransaction();
        UserAccount userAccount = userAccountDao.findById(id);
        userAccountDao.commitTransaction();
        return userAccount;
    }

    public void delete(Long id) {
        userAccountDao.getSessionwithTransaction();
        UserAccount userAccount = userAccountDao.findById(id);
        userAccountDao.delete(userAccount);
        System.out.println("found: "+userAccount.toString());
        userAccountDao.commitTransaction();
    }

    public List<UserAccount> findAll() {
        userAccountDao.getSessionwithTransaction();
        List<UserAccount> users = userAccountDao.findAll();
        userAccountDao.commitTransaction();
        return users;
    }

    public void deleteAll() {
        userAccountDao.getSessionwithTransaction();
        userAccountDao.deleteAll();
        userAccountDao.commitTransaction();
    }

    public UserAccountDao UserAccountDao() {
        return userAccountDao;
    }
}