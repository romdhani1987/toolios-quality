package fr.romdhani.aymen.toolios.view.menu;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuToolbar extends JToolBar {
    private List<JMenu> listMenu = new ArrayList<>();

    public MenuToolbar(List<JMenu> listMenu) {
        this.listMenu = listMenu;
    }

    public MenuToolbar() {
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
