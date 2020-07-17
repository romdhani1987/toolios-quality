package fr.romdhani.aymen.toolios.core.wrapper;

/**
 * Creates en enum of company service/groups
 *
 * @author aromdhani
 */
public enum Group {

    MARKETING("marketing"),
    ELECTRONICS("Electronics"),
    SOFTWARE("Software"),
    MRI("MRI"),
    NMR("NMR"),
    FINANCE("Finance"),
    SALES("Sales"),
    ADMINISTRATION("Administration"),
    DIRECTION("Direction"),
    INSTALL("Install");
    String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
