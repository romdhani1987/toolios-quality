package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComputerModelObject extends AbstractTableModel {
    private final List<Computer> computers = new ArrayList<Computer>();

    private final String[] header = {"ID", "name", "Serial number", "Operating System", "Processor", "Ram", "Service tag", "Age", "Shifting", "Purchase date", "User"};

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
                return computers.get(rowIndex).getOs();
            case 4:
                return computers.get(rowIndex).getProcessor();
            case 5:
                return computers.get(rowIndex).getRam();
            case 6:
                return computers.get(rowIndex).getServiceTag();
            case 7:
                return computers.get(rowIndex).getAge();
            case 8:
                return computers.get(rowIndex).isShifting();
            case 9:
                return computers.get(rowIndex).getPurchaseDate();
            case 10:
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

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0://id
                return Integer.class;
            case 1://name
                return String.class;
            case 2:// serial number
                return String.class;
            case 3://Operating System"
                return String.class;
            case 4://Processor
                return String.class;
            case 5://Ram
                return String.class;
            case 6://Service tag
                return String.class;
            case 7://Age
                return Integer.class;
            case 8://Shifting
                return Boolean.class;
            case 9://Purchase date
                return Date.class;
            case 10://User
                return UserAccount.class;
            default:
                return Object.class;
        }
    }
}