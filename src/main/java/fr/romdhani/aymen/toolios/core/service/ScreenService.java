package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.ScreenDao;
import fr.romdhani.aymen.toolios.core.orm.Screen;

import java.util.List;

public class ScreenService {

    private static ScreenDao screenDao;

    public ScreenService() {
        screenDao = new ScreenDao();
    }

    public boolean persist(Screen entity) {
        try {
            screenDao.persist(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }


    public void update(Screen entity) {
        screenDao.update(entity);
    }

    public Screen findById(Long id) {
        Screen screen = screenDao.findById(id);
        return screen;
    }

    public void delete(Long id) {
        Screen screen = screenDao.findById(id);
        System.out.println("Screen user: " + screen.toString());
        screenDao.delete(screen);
    }

    public List<Screen> findAll() {
        List<Screen> screens = screenDao.findAll();
        return screens;
    }

    public void deleteAll() {
        screenDao.deleteAll();
    }

    public ScreenDao UserAccountDao() {
        return screenDao;
    }
}