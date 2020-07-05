package fr.romdhani.aymen.toolios.view.menu;

import fr.romdhani.aymen.toolios.controller.MenuController;
import fr.romdhani.aymen.toolios.view.utils.IconResource;

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
        openMenuItem.setIcon(IconResource.getImage(IconResource.ICON.LOAD));
        menuFile.add(openMenuItem);

        JMenuItem closeMenuItem = new JMenuItem("Exit");
        closeMenuItem.setIcon(IconResource.getImage(IconResource.ICON.EXIT));
        menuFile.add(closeMenuItem);
        closeMenuItem.addActionListener(e -> close());

        JMenuItem exportMenuItem = new JMenuItem("Export");
        exportMenuItem.setIcon(IconResource.getImage(IconResource.ICON.EXPORT));
        menuFile.add(exportMenuItem);


        listMenu.add(menuFile);
        JMenu menuConnection = new JMenu("Connection");
        listMenu.add(menuConnection);
        JMenu settingsFile = new JMenu("Settings");
        JMenuItem dbMenuItem = new JMenuItem("Database settings");
        settingsFile.add(dbMenuItem);
        listMenu.add(settingsFile);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.setIcon(IconResource.getImage(IconResource.ICON.HELP));

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
