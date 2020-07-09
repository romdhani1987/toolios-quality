package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ComputerController;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.EditComputerDialog;
import fr.romdhani.aymen.toolios.view.dialog.informatique.NewComputerDialog;
import fr.romdhani.aymen.toolios.view.table.model.ComputerModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ComputersPanel extends JPanel {
    private JTable computersTable;
    private ComputerModelObject computerModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton updateButton;
    private ComputerController computerController;

    private void initComponents() {
        setLayout(new MigLayout());
        computerModelObject = new ComputerModelObject();
        computersTable = new JTable(computerModelObject);
        addButton = new TooliosButton("Add");
        addButton.addActionListener(e -> {
            addComputer();
        });
        editButton = new TooliosButton("Edit");
        editButton.addActionListener(e -> {
            editComputer();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.addActionListener(e -> {
            deleteComputer();
        });
        updateButton = new TooliosButton("Refresh");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        this.add(new JScrollPane(computersTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void deleteComputer() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want really to delete this computer?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = computersTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No computer were selected to delete!", "Delete computer", 1);
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
            JOptionPane.showMessageDialog(this, "No computer were selected to edit!", "Edit user", 1);
        } else {
            int modelRow = computersTable.convertRowIndexToModel(row);
            Computer computer = computerModelObject.getComputer(modelRow);
            EditComputerDialog editComputerDialog = new EditComputerDialog(computer);
            editComputerDialog.setModal(true);
            editComputerDialog.setLocationRelativeTo(null);
            editComputerDialog.setVisible(true);
            Computer computerToEdit = editComputerDialog.getComputerSupplierValid().get();
            if (computerToEdit != null && computerController.addComputerToDb(computerToEdit)) {
                JOptionPane.showMessageDialog(null, "The use has been edited successfully!", "Confirm", 2);
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
                JOptionPane.showMessageDialog(this, "Failed to add this user!",
                        "Delete user", JOptionPane.ERROR_MESSAGE);
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
