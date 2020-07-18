package fr.romdhani.aymen.toolios.core.wrapper;

/**
 * Create the list of company.
 *
 * @author aromdhani
 */
public enum CompanyEnum {
    RS2D("RS2D", "452063407", "45206340700038");
    private String name;
    private String siren;
    private String siret;

    CompanyEnum(String name, String siren, String siret) {
        this.name = name;
        this.siren = siren;
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public String getSiren() {
        return siren;
    }

    public String getSiret() {
        return siret;
    }
}
