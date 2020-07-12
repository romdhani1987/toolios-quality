package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.ScreenController;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.NewComputerDialog;
import fr.romdhani.aymen.toolios.view.dialog.informatique.ScreenDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.ScreenModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ScreensPanel extends JPanel implements TooliosView {

    private ScreenController screenController;

    public ScreensPanel(ScreenController screenController) {
        super();
        this.screenController = screenController;
        initComponents();
    }

    private JTable screensTable;
    private ScreenModelObject screenModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton updateButton;

    public ScreenModelObject getScreenModelObject() {
        return screenModelObject;
    }

    private void initComponents() {
        setLayout(new MigLayout());
        screenModelObject = new ScreenModelObject();
        screensTable = new JTable(screenModelObject);
        addButton = new TooliosButton("Add");
        addButton.addActionListener(e -> {
            addScreen();
        });
        editButton = new TooliosButton("Edit");
        editButton.addActionListener(e -> {
            editScreen();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.addActionListener(e -> {
            deleteScreen();
        });
        updateButton = new TooliosButton("Refresh");
        updateButton.addActionListener(e -> {
            refresh();
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        this.add(new JScrollPane(screensTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void refresh() {
    }

    private void deleteScreen() {
    }

    private void editScreen() {
    }

    private void addScreen() {
        ScreenDialog screenDialog = new ScreenDialog();
        screenDialog.setModal(true);
        screenDialog.setLocationRelativeTo(null);
        screenDialog.setVisible(true);
        if (screenDialog.getScreenSupplierValid().get() != null) {
            Screen screen = screenDialog.getScreenSupplierValid().get();
            if (screenController.addScreenToDb(screen)) {
                JOptionPane.showMessageDialog(null, "The screen has been added successfully!", "Confirm", 2);
                screenModelObject.addScreen(screen);
                screenModelObject.fireTableDataChanged();
                screensTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the screen!",
                        "Add Screen", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
