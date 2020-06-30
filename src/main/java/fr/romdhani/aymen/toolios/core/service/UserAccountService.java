package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dal.UserAccountDao;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import java.util.List;

public class UserAccountService {

    private static UserAccountDao userAccountDao;

    public UserAccountService() {
        userAccountDao = new UserAccountDao();
    }

    public void persist(UserAccount entity) {
        userAccountDao.openCurrentSessionwithTransaction();
        userAccountDao.persist(entity);
        userAccountDao.closeCurrentSessionwithTransaction();
    }

    public void update(UserAccount entity) {
        userAccountDao.openCurrentSessionwithTransaction();
        userAccountDao.update(entity);
        userAccountDao.closeCurrentSessionwithTransaction();
    }

    public UserAccount findById(String id) {
        userAccountDao.openCurrentSession();
        UserAccount userAccount = userAccountDao.findById(id);
        userAccountDao.closeCurrentSession();
        return userAccount;
    }

    public void delete(String id) {
        userAccountDao.openCurrentSessionwithTransaction();
        UserAccount userAccount = userAccountDao.findById(id);
        userAccountDao.delete(userAccount);
        userAccountDao.closeCurrentSessionwithTransaction();
    }

    public List<UserAccount> findAll() {
        userAccountDao.openCurrentSession();
        List<UserAccount> users = userAccountDao.findAll();
        userAccountDao.closeCurrentSession();
        return users;
    }

    public void deleteAll() {
        userAccountDao.openCurrentSessionwithTransaction();
        userAccountDao.deleteAll();
        userAccountDao.closeCurrentSessionwithTransaction();
    }

    public UserAccountDao UserAccountDao() {
        return userAccountDao;
    }
}