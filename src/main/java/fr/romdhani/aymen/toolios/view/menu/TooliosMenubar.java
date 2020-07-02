package fr.romdhani.aymen.toolios.view.menu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TooliosMenubar extends JMenuBar {
    private List<JMenu> listMenu = new ArrayList<>();

    public TooliosMenubar(List<JMenu> listMenu) {
        this.listMenu = listMenu;
    }

    public TooliosMenubar() {
        super();
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

    public void validateMenu() {
        listMenu.forEach(menu -> {
            this.add(menu);
        });
    }
}
