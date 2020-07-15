package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.License;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MotherBoardModelObject extends AbstractTableModel {
    private final List<License> licenses = new ArrayList<License>();

    private final String[] header = {"Id", "Name", "Type/Brand", "Serial number", "Purchase date", "Expiration date", "Serialized properties", "Computer"};

    public MotherBoardModelObject() {
        super();
    }

    public int getRowCount() {
        return licenses.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return licenses.get(rowIndex).getId();
            case 1:
                return licenses.get(rowIndex).getName();
            case 2:
                return licenses.get(rowIndex).getType();
            case 3:
                return licenses.get(rowIndex).getSerialNumber();
            case 4:
                return licenses.get(rowIndex).getPurchaseDate();
            case 5:
                return licenses.get(rowIndex).getExpirationDate();
            case 6:
                return licenses.get(rowIndex).getSerializedProperties();
            case 7:
                return licenses.get(rowIndex).getComputer();
            case 8:
            default:
                return null;
        }
    }

    public void addAllLicenses(List<License> screensList) {
        screensList.forEach(license -> {
            if (!licenses.contains(license)) {
                licenses.add(license);
                fireTableRowsInserted(licenses.size() - 1, licenses.size() - 1);
            }
        });
    }

    public void addLicense(License license) {
        licenses.add(license);
        fireTableRowsInserted(licenses.size() - 1, licenses.size() - 1);
    }


    public void deleteScreen(int rowIndex) {
        licenses.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public License getLicense(int i) {
        return licenses.get(i);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0://id
                return Integer.class;
            case 1://name
                return String.class;
            case 2:// type
                return String.class;
            case 3://serial number
                return String.class;
            case 4://purchase date
                return String.class;
            case 5://expiration date
                return Date.class;
            case 6://serialized properties
                return String.class;
            case 7://computer
                return Computer.class;
            default:
                return Object.class;
        }
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }
}