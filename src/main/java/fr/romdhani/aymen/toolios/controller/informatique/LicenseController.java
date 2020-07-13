package fr.romdhani.aymen.toolios.controller.informatique;

import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.core.service.LicenseService;
import fr.romdhani.aymen.toolios.view.panel.informatique.LicensesPanel;

public class LicenseController {
    private LicensesPanel licensesPanel;
    private LicenseService licenseService;

    public LicensesPanel getLicensesPanel() {
        return licensesPanel;
    }

    public LicenseService getLicenseService() {
        return licenseService;
    }

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
        initComponents();
    }

    private void initComponents() {
        licensesPanel = new LicensesPanel(this);
        licensesPanel.getLicensesModelObject().addAllLicenses(licenseService.findAll());
    }

    public boolean addLicenseToDb(License license) {
        return licenseService.persist(license);
    }

    public void deleteLicenseFromDb(License license) {
        licenseService.delete(license.getId());
    }

    public void refresh() {
        licensesPanel.getLicensesModelObject().addAllLicenses(licenseService.findAll());
    }

    public void deleteAllLicensesFromDb() {
        licenseService.deleteAll();
    }
}
