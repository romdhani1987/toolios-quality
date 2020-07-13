package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.LicenseController;
import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.LicenseDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.LicenseModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LicensesPanel extends JPanel implements TooliosView {

    private LicenseController licenseController;

    public LicensesPanel(LicenseController licenseController) {
        super();
        this.licenseController = licenseController;
        initComponents();
    }

    private JTable licensesTable;
    private LicenseModelObject licensesModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton refreshButton;

    public LicenseController getLicenseController() {
        return licenseController;
    }

    public LicenseModelObject getLicensesModelObject() {
        return licensesModelObject;
    }

    private void initComponents() {
        setLayout(new MigLayout());
        licensesModelObject = new LicenseModelObject();
        licensesTable = new JTable(licensesModelObject);
        addButton = new TooliosButton("Add");
        addButton.addActionListener(e -> {
            addLicense();
        });
        editButton = new TooliosButton("Edit");
        editButton.addActionListener(e -> {
            editLicense();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.addActionListener(e -> {
            deleteLicense();
        });
        refreshButton = new TooliosButton("Refresh");
        refreshButton.addActionListener(e -> {
            refresh();
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(refreshButton);
        this.add(new JScrollPane(licensesTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void refresh() {
    }

    private void deleteLicense() {
    }

    private void editLicense() {
        int row = licensesTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No license were selected to edit!", "Edit License", 1);
        } else {
            int modelRow = licensesTable.convertRowIndexToModel(row);
            License license = licensesModelObject.getLicense(modelRow);
            LicenseDialog editLicenseDialog = new LicenseDialog(license, true);
            editLicenseDialog.setModal(true);
            editLicenseDialog.setLocationRelativeTo(null);
            editLicenseDialog.setVisible(true);
            License screenToEdit = editLicenseDialog.getLicenseSupplierValid().get();
            if (screenToEdit != null && licenseController.addScreenToDb(screenToEdit)) {
                JOptionPane.showMessageDialog(null, "The changes have been applied successfully!", "Confirm", 2);
                licensesModelObject.fireTableDataChanged();
                licensesTable.repaint();
            }
        }
    }

    private void addLicense() {
        LicenseDialog licenseDialog = new LicenseDialog();
        licenseDialog.setModal(true);
        licenseDialog.setLocationRelativeTo(null);
        licenseDialog.setVisible(true);
        if (licenseDialog.getLicenseSupplierCancel().get() != null) {
            License license = licenseDialog.getLicenseSupplierCancel().get();
            if (licenseController.addScreenToDb(license)) {
                JOptionPane.showMessageDialog(null, "The license has been added successfully!", "Confirm", 2);
                licensesModelObject.addLicense(license);
                licensesModelObject.fireTableDataChanged();
                licensesTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the license !",
                        "Add License", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
