package fr.romdhani.aymen.toolios.controller;

import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.core.service.ScreenService;
import fr.romdhani.aymen.toolios.view.panel.ScreensPanel;

import java.util.List;

public class ScreenController implements Controller<Screen> {
    private ScreensPanel screensPanel;
    private ScreenService screenService;

    public ScreensPanel getScreensPanel() {
        return screensPanel;
    }

    public void setScreensPanel(ScreensPanel screensPanel) {
        this.screensPanel = screensPanel;
    }

    public ScreenService getScreenService() {
        return screenService;
    }

    public void setScreenService(ScreenService screenService) {
        this.screenService = screenService;
    }

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
        screensPanel = new ScreensPanel(this);
    }

    public void addToDB(Screen screen) {
    }

    public void addAllToDB(List<Screen> list) {
    }

    public void findFromDB(Long i) {
    }

    public void deleteAll() {
    }

    public void deleteFromDB(Long i) {
    }
}
