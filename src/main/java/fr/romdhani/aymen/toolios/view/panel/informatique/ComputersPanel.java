package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.ComputerController;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.EditComputerDialog;
import fr.romdhani.aymen.toolios.view.dialog.informatique.NewComputerDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.ComputerModelObject;
import fr.romdhani.aymen.toolios.view.utils.IconResource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ComputersPanel extends JPanel implements TooliosView {
    private JTable computersTable;
    private ComputerModelObject computerModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton refreshButton;
    private ComputerController computerController;

    private void initComponents() {
        setLayout(new MigLayout());
        computerModelObject = new ComputerModelObject();
        computersTable = new JTable(computerModelObject);
        computersTable.setAutoCreateRowSorter(true);

        addButton = new TooliosButton("Add");
        addButton.setIcon(IconResource.getImage(IconResource.ICON.PLUS));
        addButton.addActionListener(e -> {
            addComputer();
        });
        editButton = new TooliosButton("Edit");
        editButton.setIcon(IconResource.getImage(IconResource.ICON.EDIT_SMALL));
        editButton.addActionListener(e -> {
            editComputer();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.setIcon(IconResource.getImage(IconResource.ICON.DELETE_BLUE));
        removeButton.addActionListener(e -> {
            deleteComputer();
        });
        refreshButton = new TooliosButton("Refresh");
        refreshButton.setIcon(IconResource.getImage(IconResource.ICON.REFRESH));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(refreshButton);
        this.add(new JScrollPane(computersTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void deleteComputer() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want really to delete this computer?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = computersTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No computer were selected to delete!", "Delete Computer", 1);
            } else {
                int modelRow = computersTable.convertRowIndexToModel(row);
                Computer computer = computerModelObject.getComputer(modelRow);
                computerController.deleteComputerFromDb(computer);
                computerModelObject.removeComputer(modelRow);
            }
        }
    }

    private void editComputer() {

        int row = computersTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No computer were selected to edit!", "Edit Computer", 1);
        } else {
            int modelRow = computersTable.convertRowIndexToModel(row);
            Computer computer = computerModelObject.getComputer(modelRow);
            EditComputerDialog editComputerDialog = new EditComputerDialog(computer);
            editComputerDialog.setModal(true);
            editComputerDialog.setLocationRelativeTo(null);
            editComputerDialog.setVisible(true);
            Computer computerToEdit = editComputerDialog.getComputerSupplierValid().get();
            if (computerToEdit != null && computerController.addComputerToDb(computerToEdit)) {
                JOptionPane.showMessageDialog(null, "The changes have been applied successfully!", "Confirm", 2);
                computerModelObject.fireTableDataChanged();
                computersTable.repaint();
            }
        }
    }

    private void addComputer() {
        NewComputerDialog newComputerDialog = new NewComputerDialog();
        newComputerDialog.setModal(true);
        newComputerDialog.setLocationRelativeTo(null);
        newComputerDialog.setVisible(true);
        if (newComputerDialog.getComputerSupplierValid().get() != null) {
            Computer computer = newComputerDialog.getComputerSupplierValid().get();
            if (computerController.addComputerToDb(computer)) {
                JOptionPane.showMessageDialog(null, "The computer has been added successfully!", "Confirm", 2);
                computerModelObject.addComputer(computer);
                computerModelObject.fireTableDataChanged();
                computersTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the computer!",
                        "New Computer", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public ComputersPanel(ComputerController computerControllerl) {
        super();
        this.computerController = computerControllerl;
        initComponents();
    }

    public JTable getComputersTable() {
        return computersTable;
    }

    public void setComputersTable(JTable computersTable) {
        this.computersTable = computersTable;
    }

    public ComputerModelObject getComputerModelObject() {
        return computerModelObject;
    }

    public void setComputerModelObject(ComputerModelObject computerModelObject) {
        this.computerModelObject = computerModelObject;
    }
}
