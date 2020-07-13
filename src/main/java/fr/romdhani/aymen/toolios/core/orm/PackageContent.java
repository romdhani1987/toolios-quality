package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class PackageContent implements Serializable {
    private String cablesIncluded;
    private String driversIncluded;

    public PackageContent() {
    }

    public String getCablesIncluded() {
        return cablesIncluded;
    }

    public void setCablesIncluded(String cablesIncluded) {
        this.cablesIncluded = cablesIncluded;
    }

    public String getDriversIncluded() {
        return driversIncluded;
    }

    public void setDriversIncluded(String driversIncluded) {
        this.driversIncluded = driversIncluded;
    }
}
