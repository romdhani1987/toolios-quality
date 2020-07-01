package fr.romdhani.aymen.toolios.view.panel;

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
        tabbedPane = new JTabbedPane();
        computersPanel = new ComputersPanel();
        screensPanel = new ScreensPanel();
        usersPanel = new UsersPanel();
        othersEquipementPanel = new OthersEquipementPanel();
        add("Users", usersPanel);
        add("Computers", computersPanel);
        add("Screens", screensPanel);
        add("other equipments", othersEquipementPanel);
        add(tabbedPane);
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
