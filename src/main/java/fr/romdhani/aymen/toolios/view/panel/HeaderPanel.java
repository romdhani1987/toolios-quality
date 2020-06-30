package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.view.menu.TooliosMenubar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeaderPanel extends JPanel {
    TooliosMenubar menuToolbar = new TooliosMenubar();

    public HeaderPanel() {
        super();
        initComponents();
    }

    public void initComponents() {
        setLayout(new MigLayout());
        add(menuToolbar, "grow, span");
    }

}
