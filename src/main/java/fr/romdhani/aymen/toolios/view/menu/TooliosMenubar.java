package fr.romdhani.aymen.toolios.view.menu;

import fr.romdhani.aymen.toolios.controller.MenuController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TooliosMenubar extends JMenuBar {
    private List<JMenu> listMenu = new ArrayList<>();
    private MenuController menuController;

    public TooliosMenubar(List<JMenu> listMenu) {
        this.listMenu = listMenu;
    }

    public TooliosMenubar(MenuController menuController) {
        super();
        this.menuController = menuController;
        initComponents();
        validateMenu();
    }

    public void addMenu(JMenu menu) {
        listMenu.add(menu);
    }

    public void initComponents() {
        JMenu menuFile = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        menuFile.add(openMenuItem);
        JMenuItem closeMenuItem = new JMenuItem("Exit");
        menuFile.add(closeMenuItem);
        closeMenuItem.addActionListener(e -> close());
        JMenuItem exportMenuItem = new JMenuItem("Export");
        menuFile.add(exportMenuItem);
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        menuFile.add(saveAsMenuItem);
        listMenu.add(menuFile);
        JMenu menuConnection = new JMenu("Connection");
        listMenu.add(menuConnection);
        JMenu settingsFile = new JMenu("Settings");
        JMenuItem dbMenuItem = new JMenuItem("Database settings");
        settingsFile.add(dbMenuItem);
        listMenu.add(settingsFile);
        JMenu menuHelp = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("?");
        menuHelp.add(helpMenuItem);
        listMenu.add(menuHelp);
    }

    private void close() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want really to exit Toolios-quality?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            menuController.close();
        }
    }

    public void validateMenu() {
        listMenu.forEach(menu -> {
            this.add(menu);
        });
    }
}
