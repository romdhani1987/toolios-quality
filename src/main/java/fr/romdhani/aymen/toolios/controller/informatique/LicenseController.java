package fr.romdhani.aymen.toolios.controller.informatique;

import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.core.service.LicenseService;
import fr.romdhani.aymen.toolios.core.service.ScreenService;
import fr.romdhani.aymen.toolios.view.panel.informatique.LicensesPanel;
import fr.romdhani.aymen.toolios.view.panel.informatique.ScreensPanel;

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

    public boolean addScreenToDb(License license) {
        return licenseService.persist(license);
    }

    public void deleteScreenFromDb(Screen screen) {
        licenseService.delete(screen.getId());
    }

    public void refresh() {
        licensesPanel.getLicensesModelObject().addAllLicenses(licenseService.findAll());
    }

    public void deleteAllScreensFromDb() {
        licenseService.deleteAll();
    }
}
