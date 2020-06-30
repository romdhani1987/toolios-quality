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
        JMenu menuConnection = new JMenu("Connection");
        listMenu.add(menuConnection);
        JMenu menuFile = new JMenu("File");
        listMenu.add(menuFile);
        JMenu settingsFile = new JMenu("Settings");
        listMenu.add(settingsFile);
        JMenu menuHelp = new JMenu("Help");
        listMenu.add(menuHelp);
    }

    public void validateMenu() {
        listMenu.forEach(menu -> {
            this.add(menu);
        });
    }
}
