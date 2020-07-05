package fr.romdhani.aymen.toolios.controller;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.core.service.ComputerService;
import fr.romdhani.aymen.toolios.view.panel.ComputersPanel;

public class ComputerController {
    private ComputersPanel computersPanel;
    private ComputerService computerService;

    public ComputersPanel getComputersPanel() {
        return computersPanel;
    }

    public void setComputersPanel(ComputersPanel computersPanel) {
        this.computersPanel = computersPanel;
    }

    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    public ComputerController(ComputerService userAccountService) {
        this.computerService = userAccountService;
        initComponents();
    }

    private void initComponents() {
        computersPanel = new ComputersPanel(this);
        computersPanel.getComputerModelObject().addAllComputers(computerService.findAll());
    }

    public boolean addComputerToDb(Computer computer) {
        return computerService.persist(computer);
    }

    public void deleteUserFromDb(UserAccount user) {
        computerService.delete(user.getId());
    }

    public void refresh() {
        computersPanel.getComputerModelObject().addAllComputers(computerService.findAll());
    }

    public void deleteAllUserFromDb() {
        computerService.deleteAll();
    }
}
