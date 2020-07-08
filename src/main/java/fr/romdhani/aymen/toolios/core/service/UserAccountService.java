package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.UserAccountDao;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import java.util.List;


public class UserAccountService {

    private static UserAccountDao userAccountDao;

    public UserAccountService() {
        userAccountDao = new UserAccountDao();
    }

    public boolean persist(UserAccount entity) {
        try {
            userAccountDao.persist(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }


    public void update(UserAccount entity) {
        userAccountDao.update(entity);
    }

    public UserAccount findById(Long id) {
        UserAccount userAccount = userAccountDao.findById(id);
        return userAccount;
    }

    public void delete(Long id) {
        UserAccount userAccount = userAccountDao.findById(id);
        System.out.println("found user: " + userAccount.toString());
        userAccountDao.delete(userAccount);
    }

    public List<UserAccount> findAll() {
        List<UserAccount> users = userAccountDao.findAll();
        return users;
    }

    public void deleteAll() {
        userAccountDao.deleteAll();
    }
    public UserAccount findByLogin(String login){
        return userAccountDao.findByLogin(login);
    }

    public UserAccountDao UserAccountDao() {
        return userAccountDao;
    }
}