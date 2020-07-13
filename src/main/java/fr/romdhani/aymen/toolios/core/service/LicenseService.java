package fr.romdhani.aymen.toolios.core.service;

import fr.romdhani.aymen.toolios.core.dao.LicenseDao;
import fr.romdhani.aymen.toolios.core.dao.ScreenDao;
import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.core.orm.Screen;

import java.util.List;

public class LicenseService {

    private static LicenseDao licenseDao;

    public LicenseService() {
        licenseDao = new LicenseDao();
    }

    public boolean persist(License entity) {
        try {
            licenseDao.persist(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }


    public void update(License entity) {
        licenseDao.update(entity);
    }

    public License findById(Long id) {
        License license = licenseDao.findById(id);
        return license;
    }

    public void delete(Long id) {
        License license = licenseDao.findById(id);
        System.out.println("Screen user: " + license.toString());
        licenseDao.delete(license);
    }

    public List<License> findAll() {
        List<License> licenses = licenseDao.findAll();
        return licenses;
    }

    public void deleteAll() {
        licenseDao.deleteAll();
    }

    public LicenseDao licenseDao() {
        return licenseDao;
    }
}