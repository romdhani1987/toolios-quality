package fr.romdhani.aymen.toolios.controller.informatique;

import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.core.service.ScreenService;
import fr.romdhani.aymen.toolios.view.panel.informatique.ScreensPanel;

public class ScreenController {
    private ScreensPanel screensPanel;
    private ScreenService screenService;

    public ScreensPanel getScreensPanel() {
        return screensPanel;
    }

    public ScreenService getComputerService() {
        return screenService;
    }

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
        screensPanel = new ScreensPanel(this);
    }

    public boolean addScreenToDb(Screen screen) {
        return screenService.persist(screen);
    }

    public void deleteScreenFromDb(Screen screen) {
        screenService.delete(screen.getId());
    }

    public void refresh() {
        screensPanel.getScreenModelObject().addAllScreens(screenService.findAll());
    }

    public void deleteAllScreensFromDb() {
        screenService.deleteAll();
    }
}
