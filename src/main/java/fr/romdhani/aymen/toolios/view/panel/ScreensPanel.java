package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ScreenController;
import fr.romdhani.aymen.toolios.controller.UserController;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.table.model.ScreenModelObject;
import fr.romdhani.aymen.toolios.view.table.model.UserModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ScreensPanel extends JPanel {

    private ScreenController screenController;

    public ScreensPanel(ScreenController screenController) {
        super();
        this.screenController = screenController;
        initComponents();
    }

    private JTable screensTable;
    private ScreenModelObject userModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton updateButton;
    private UserController userController;

    private void initComponents() {
        setLayout(new MigLayout());
        userModelObject = new ScreenModelObject();
        screensTable = new JTable(userModelObject);
        addButton = new TooliosButton("Add");
        editButton = new TooliosButton("Edit");
        removeButton = new TooliosButton("Delete");
        updateButton = new TooliosButton("Refresh");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        this.add(new JScrollPane(screensTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }
}
