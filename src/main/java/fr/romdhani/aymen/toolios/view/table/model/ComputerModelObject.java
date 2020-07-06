package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ComputerModelObject extends AbstractTableModel {
    private final List<Computer> computers = new ArrayList<Computer>();

    private final String[] header = {"ID", "name", "Serial number", "Processor", "Ram", "Service tag", "Age", "Shifting", "Purchase date", "User"};

    public ComputerModelObject() {
        super();
    }

    public int getRowCount() {
        return computers.size();
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
                return computers.get(rowIndex).getId();
            case 1:
                return computers.get(rowIndex).getName();
            case 2:
                return computers.get(rowIndex).getSerialNumber();
            case 3:
                return computers.get(rowIndex).getProcessor();
            case 4:
                return computers.get(rowIndex).getRam();
            case 5:
                return computers.get(rowIndex).getServiceTag();
            case 6:
                return computers.get(rowIndex).getAge();
            case 7:
                return computers.get(rowIndex).isShifting();
            case 8:
                return computers.get(rowIndex).getPurchaseDate();
            case 9:
                return computers.get(rowIndex).getUserAccount();
            default:
                return null;
        }
    }

    public void addAllComputers(List<Computer> userAccountList) {
        userAccountList.forEach(computer -> {
            if (!computers.contains(computer)) {
                computers.add(computer);
                fireTableRowsInserted(computers.size() - 1, computers.size() - 1);
            }
        });
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
        fireTableRowsInserted(computers.size() - 1, computers.size() - 1);
    }

    public void removeComputer(int rowIndex) {
        computers.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Computer getComputer(int i) {
        return computers.get(i);
    }
}