package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ComputerController;
import fr.romdhani.aymen.toolios.controller.UserController;
import fr.romdhani.aymen.toolios.core.service.ComputerService;
import fr.romdhani.aymen.toolios.core.service.UserAccountService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private ComputersPanel computersPanel;
    private ScreensPanel screensPanel;
    private UsersPanel usersPanel;
    private OthersEquipementPanel othersEquipementPanel;

    public MainPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout());
        tabbedPane = new JTabbedPane();
        // users
        UserAccountService userAccountService = new UserAccountService();
        usersPanel = new UserController(userAccountService).getUsersPanel();
        // computers
        ComputerService computerService = new ComputerService();
        ComputerController computerController = new ComputerController(computerService);
        computersPanel = computerController.getComputersPanel();
        //Screens
        screensPanel = new ScreensPanel();
        othersEquipementPanel = new OthersEquipementPanel();
        // others Equipements
        tabbedPane.add("Users", usersPanel);
        tabbedPane.add("Computers", computersPanel);
        tabbedPane.add("Screens", screensPanel);
        tabbedPane.add("Other equipments", othersEquipementPanel);
        add(tabbedPane, "grow,span, push");
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public ComputersPanel getComputersPanel() {
        return computersPanel;
    }

    public void setComputersPanel(ComputersPanel computersPanel) {
        this.computersPanel = computersPanel;
    }

    public ScreensPanel getScreensPanel() {
        return screensPanel;
    }

    public void setScreensPanel(ScreensPanel screensPanel) {
        this.screensPanel = screensPanel;
    }

    public UsersPanel getUsersPanel() {
        return usersPanel;
    }

    public void setUsersPanel(UsersPanel usersPanel) {
        this.usersPanel = usersPanel;
    }

    public OthersEquipementPanel getOthersEquipementPanel() {
        return othersEquipementPanel;
    }

    public void setOthersEquipementPanel(OthersEquipementPanel othersEquipementPanel) {
        this.othersEquipementPanel = othersEquipementPanel;
    }
}
