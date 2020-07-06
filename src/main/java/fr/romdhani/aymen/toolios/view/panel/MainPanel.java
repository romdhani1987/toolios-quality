package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ComputerController;
import fr.romdhani.aymen.toolios.controller.ScreenController;
import fr.romdhani.aymen.toolios.controller.UserController;
import fr.romdhani.aymen.toolios.core.service.ComputerService;
import fr.romdhani.aymen.toolios.core.service.ScreenService;
import fr.romdhani.aymen.toolios.core.service.UserAccountService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {
    private JTabbedPane devicestabbedPane;
    private JTabbedPane requestTabbedPane;
    private JTabbedPane projectTabbedPane;
    private JTabbedPane mainTabbedPane;
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
        mainTabbedPane = new JTabbedPane();
        projectTabbedPane = new JTabbedPane();
        requestTabbedPane = new JTabbedPane();

        devicestabbedPane = new JTabbedPane();
        // users
        UserAccountService userAccountService = new UserAccountService();
        usersPanel = new UserController(userAccountService).getUsersPanel();
        // computers
        ComputerService computerService = new ComputerService();
        computersPanel = new ComputerController(computerService).getComputersPanel();
        //Screens
        ScreenService screenService = new ScreenService();
        screensPanel = new ScreenController(screenService).getScreensPanel();
        // other Equipments
        othersEquipementPanel = new OthersEquipementPanel();

        devicestabbedPane.add("Users", usersPanel);
        devicestabbedPane.add("Computers", computersPanel);
        devicestabbedPane.add("Screens", screensPanel);
        devicestabbedPane.add("Other equipments", othersEquipementPanel);

        mainTabbedPane.add("Admin", devicestabbedPane);
        mainTabbedPane.add("Projects", projectTabbedPane);
        mainTabbedPane.add("Requests", requestTabbedPane);

        add(mainTabbedPane, "grow,span, push");
    }

}
