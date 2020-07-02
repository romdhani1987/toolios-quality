package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.ScreenDao;
import fr.romdhani.aymen.toolios.core.orm.Screen;

import java.util.List;

public class ScreenService {

    private static ScreenDao screenDao;

    public ScreenService() {
        screenDao = new ScreenDao();
    }

    public void persist(Screen entity) {
        screenDao.openCurrentSessionwithTransaction();
        screenDao.persist(entity);
        screenDao.closeCurrentSessionwithTransaction();
    }

    public void update(Screen entity) {
        screenDao.openCurrentSessionwithTransaction();
        screenDao.update(entity);
        screenDao.closeCurrentSessionwithTransaction();
    }

    public Screen findById(String id) {
        screenDao.openCurrentSession();
        Screen screen = screenDao.findById(id);
        screenDao.closeCurrentSession();
        return screen;
    }

    public void delete(String id) {
        screenDao.openCurrentSessionwithTransaction();
        Screen screen = screenDao.findById(id);
        screenDao.delete(screen);
        screenDao.closeCurrentSessionwithTransaction();
    }

    public List<Screen> findAll() {
        screenDao.openCurrentSession();
        List<Screen> screens = screenDao.findAll();
        screenDao.closeCurrentSession();
        return screens;
    }

    public void deleteAll() {
        screenDao.openCurrentSessionwithTransaction();
        screenDao.deleteAll();
        screenDao.closeCurrentSessionwithTransaction();
    }

    public ScreenDao screenDao() {
        return screenDao;
    }
}