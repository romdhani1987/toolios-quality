package fr.romdhani.aymen.toolios.controller;

import fr.romdhani.aymen.toolios.core.service.ComputerService;
import fr.romdhani.aymen.toolios.view.panel.ComputersPanel;

public class ComputerController {
    private ComputersPanel computersPanel;
    private ComputerService userAccountService;

    public ComputersPanel getComputersPanel() {
        return computersPanel;
    }

    public void setComputersPanel(ComputersPanel computersPanel) {
        this.computersPanel = computersPanel;
    }

    public ComputerService getUserAccountService() {
        return userAccountService;
    }

    public void setUserAccountService(ComputerService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public ComputerController(ComputerService userAccountService) {
        this.userAccountService = userAccountService;
        initComponents();
    }
    private void initComponents() {
        computersPanel= new ComputersPanel(this);
        computersPanel.getComputerModelObject().addAllComputers(userAccountService.findAll());
    }
}
