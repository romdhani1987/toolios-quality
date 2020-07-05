package fr.romdhani.aymen.toolios.view.panel;

import fr.romdhani.aymen.toolios.controller.ComputerController;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
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
    }

    private void editComputer() {
    }

    private void addComputer() {
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
