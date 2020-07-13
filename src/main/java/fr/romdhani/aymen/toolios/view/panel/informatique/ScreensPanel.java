package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.ScreenController;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.ScreenDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.ScreenModelObject;
import fr.romdhani.aymen.toolios.view.utils.IconResource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

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
    private TooliosButton refreshButton;

    public ScreenModelObject getScreenModelObject() {
        return screenModelObject;
    }

    private void initComponents() {
        setLayout(new MigLayout());
        screenModelObject = new ScreenModelObject();
        screensTable = new JTable(screenModelObject);
        java.util.List<Computer> computers = (List<Computer>) getSession().createQuery("from Computer").list();
        // screensTable.setDefaultEditor(Computer.class, new CellComputerEditor(computers));
        screensTable.setAutoCreateRowSorter(true);

        addButton = new TooliosButton("Add");
        addButton.setIcon(IconResource.getImage(IconResource.ICON.PLUS));
        addButton.addActionListener(e -> {
            addScreen();
        });
        editButton = new TooliosButton("Edit");
        editButton.setIcon(IconResource.getImage(IconResource.ICON.EDIT_SMALL));
        editButton.addActionListener(e -> {
            editScreen();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.setIcon(IconResource.getImage(IconResource.ICON.DELETE_BLUE));
        removeButton.addActionListener(e -> {
            deleteScreen();
        });
        refreshButton = new TooliosButton("Refresh");
        refreshButton.setIcon(IconResource.getImage(IconResource.ICON.REFRESH));
        refreshButton.addActionListener(e -> {
            refresh();
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(refreshButton);
        this.add(new JScrollPane(screensTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void refresh() {
    }

    private void deleteScreen() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want really to delete the screen?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = screensTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No screen were selected to delete!", "Delete Screen", 1);
            } else {
                int modelRow = screensTable.convertRowIndexToModel(row);
                Screen screen = screenModelObject.getScreen(modelRow);
                screenController.deleteScreenFromDb(screen);
                screenModelObject.deleteScreen(modelRow);
            }
        }

    }

    private void editScreen() {
        int row = screensTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No screen were selected to edit!", "Edit Screen", 1);
        } else {
            int modelRow = screensTable.convertRowIndexToModel(row);
            Screen screen = screenModelObject.getScreen(modelRow);
            ScreenDialog editScreenDialog = new ScreenDialog(screen, true);
            editScreenDialog.setModal(true);
            editScreenDialog.setLocationRelativeTo(null);
            editScreenDialog.setVisible(true);
            Optional<Screen> screenToEditOpt = editScreenDialog.getScreenSupplierValid().get();
            if (screenToEditOpt.isPresent() && screenController.addScreenToDb(screenToEditOpt.get())) {
                JOptionPane.showMessageDialog(null, "The changes have been applied successfully!", "Confirm", 2);
                screenModelObject.fireTableDataChanged();
                screensTable.repaint();
            }
        }
    }

    private void addScreen() {
        ScreenDialog screenDialog = new ScreenDialog();
        screenDialog.setModal(true);
        screenDialog.setLocationRelativeTo(null);
        screenDialog.setVisible(true);
        if (screenDialog.getScreenSupplierValid().get() != null) {
            Optional<Screen> screenOpt = screenDialog.getScreenSupplierValid().get();
            if (screenOpt.isPresent() && screenController.addScreenToDb(screenOpt.get())) {
                JOptionPane.showMessageDialog(null, "The screen has been added successfully!", "Confirm", 2);
                screenModelObject.addScreen(screenOpt.get());
                screenModelObject.fireTableDataChanged();
                screensTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the screen!",
                        "Add Screen", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
