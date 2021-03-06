package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.LicenseController;
import fr.romdhani.aymen.toolios.controller.informatique.MotherBoardController;
import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.informatique.LicenseDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.LicenseModelObject;
import fr.romdhani.aymen.toolios.view.utils.IconResource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class MotherBoardPanel extends JPanel implements TooliosView {

    private MotherBoardController motherBoardController;

    public MotherBoardPanel(MotherBoardController licenseController) {
        super();
        this.motherBoardController = licenseController;
        initComponents();
    }

    private JTable licensesTable;
    private LicenseModelObject licensesModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton refreshButton;

    public MotherBoardController getMotherBoardController() {
        return motherBoardController;
    }

    public LicenseModelObject getLicensesModelObject() {
        return licensesModelObject;
    }

    private void initComponents() {
        setLayout(new MigLayout());
        licensesModelObject = new LicenseModelObject();
        licensesTable = new JTable(licensesModelObject);
        addButton = new TooliosButton("Add");
        addButton.setIcon(IconResource.getImage(IconResource.ICON.PLUS));
        addButton.addActionListener(e -> {
            addLicense();
        });
        editButton = new TooliosButton("Edit");
        editButton.setIcon(IconResource.getImage(IconResource.ICON.EDIT_SMALL));
        editButton.addActionListener(e -> {
            editLicense();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.setIcon(IconResource.getImage(IconResource.ICON.DELETE_BLUE));
        removeButton.addActionListener(e -> {
            deleteLicense();
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
        this.add(new JScrollPane(licensesTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void refresh() {
    }

    private void deleteLicense() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want really to delete the license ?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = licensesTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No License were selected to delete!", "Delete License", 1);
            } else {
                int modelRow = licensesTable.convertRowIndexToModel(row);
                License license = licensesModelObject.getLicense(modelRow);
                motherBoardController.deleteLicenseFromDb(license);
                licensesModelObject.deleteScreen(modelRow);
            }
        }
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
            Optional<License> screenToEditOpt = editLicenseDialog.getLicenseSupplierValid().get();
            if (screenToEditOpt.isPresent() && motherBoardController.addLicenseToDb(screenToEditOpt.get())) {
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
        if (licenseDialog.getLicenseSupplierValid().get() != null) {
            Optional<License> licenseOpt = licenseDialog.getLicenseSupplierValid().get();
            if (licenseOpt.isPresent() && motherBoardController.addLicenseToDb(licenseOpt.get())) {
                JOptionPane.showMessageDialog(null, "The license has been added successfully!", "Confirm", 2);
                licensesModelObject.addLicense(licenseOpt.get());
                licensesModelObject.fireTableDataChanged();
                licensesTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the license !",
                        "Add License", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
