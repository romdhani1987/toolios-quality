package fr.romdhani.aymen.toolios.view.panel.informatique;

import fr.romdhani.aymen.toolios.controller.informatique.LicenseController;
import fr.romdhani.aymen.toolios.controller.user.UserController;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.LicenseModelObject;
import fr.romdhani.aymen.toolios.view.table.model.ScreenModelObject;
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
    }

    private void addLicense() {
    }
}
