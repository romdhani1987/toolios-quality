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
        computersPanel = new ComputerController(computerService).getComputersPanel();
        //Screens
        screensPanel = new ScreensPanel();
        // other Equipments
        othersEquipementPanel = new OthersEquipementPanel();

        tabbedPane.add("Users", usersPanel);
        tabbedPane.add("Computers", computersPanel);
        tabbedPane.add("Screens", screensPanel);
        tabbedPane.add("Other equipments", othersEquipementPanel);
        add(tabbedPane, "grow,span, push");
    }

}
