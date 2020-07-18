package fr.romdhani.aymen.toolios.core.wrapper;

/**
 * Create the list of functions.
 *
 * @author aromdhani
 */
public enum Function {
    SOFTWARE_ENGINEER("Software Engineer"),
    ELECTRONICS_ENGINEER("Electronics Engineer"),
    ADMINISTRATIVE_MANAGER("Administrative Manager"),
    ADMINISTRATIVE_ASSISTANT("Administrative Assistant"),
    LOGISTICS_ANALYST("Logistics Analyst"),
    NMR_PRODUCT_MANAGER("NMR Product Manager"),
    CTO("Comity Technical oOfficer"),
    RESEARCH_ENGINEER("Research Engineer"),
    MRI_PRODUCT_MANAGER("MRI Product Manager"),
    SCIENTIFIC_MARKETING("Scientific Marketing"),
    SERVICE_ENGINEER("Service Engineer"),
    CUSTOMER_INSTALLATIONS("Customer Installations"),
    MRI_APPLICATION_ENGINEER("MRI Application Engineer"),
    ELECTRONICS_TECHNICIAN("Electronics Technician"),
    SENIOR_SALES_MANAGER("Senior Sales Manager"),
    FINANCE_CONTROLLER("Finance Controller"),
    SALES_MANAGER("Sales Manager");

    String name;

    Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
