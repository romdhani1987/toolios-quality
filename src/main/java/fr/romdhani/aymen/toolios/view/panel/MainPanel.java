package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ComputerController;
import fr.romdhani.aymen.toolios.controller.ScreenController;
import fr.romdhani.aymen.toolios.controller.user.UserController;
import fr.romdhani.aymen.toolios.core.service.ComputerService;
import fr.romdhani.aymen.toolios.core.service.ScreenService;
import fr.romdhani.aymen.toolios.core.service.UserAccountService;
import fr.romdhani.aymen.toolios.view.dialog.ConnectionDialog;
import fr.romdhani.aymen.toolios.view.panel.user.UsersPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JTabbedPane devicestabbedPane;
    private JTabbedPane requestTabbedPane;
    private JTabbedPane projectTabbedPane;
    private JTabbedPane mainTabbedPane;
    private ComputersPanel computersPanel;
    private ScreensPanel screensPanel;
    private UsersPanel usersPanel;
    private OthersEquipementPanel othersEquipementPanel;
    private UserController userController;

    public MainPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Users
        UserAccountService userAccountService = new UserAccountService();
        userController = new UserController(userAccountService);
        ConnectionDialog connectionDialog = new ConnectionDialog("Connection", userController);
        //
        connectionDialog.setModal(true);
        connectionDialog.setSize(new Dimension(300, 200));
        connectionDialog.setLocationRelativeTo(null);
        connectionDialog.setVisible(true);
        setLayout(new MigLayout());
        mainTabbedPane = new JTabbedPane();
        projectTabbedPane = new JTabbedPane();
        requestTabbedPane = new JTabbedPane();
        devicestabbedPane = new JTabbedPane();


        usersPanel = userController.getUsersPanel();
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
