package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.Screen;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScreenModelObject extends AbstractTableModel {
    private final List<Screen> screens = new ArrayList<Screen>();

    private final String[] header = {"Id", "Screen name", "Serial number", "Service tag", "Purchase date", "Serialized properties", "Computer"};

    public ScreenModelObject() {
        super();
    }

    public int getRowCount() {
        return screens.size();
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
                return screens.get(rowIndex).getId();
            case 1:
                return screens.get(rowIndex).getName();
            case 2:
                return screens.get(rowIndex).getSerialNumber();
            case 3:
                return screens.get(rowIndex).getServiceTag();
            case 4:
                return screens.get(rowIndex).getPurchaseDate();
            case 5:
                return screens.get(rowIndex).getSerialized_properties();
            case 6:
                return screens.get(rowIndex).getComputer();
            case 7:
            default:
                return null;
        }
    }

    public void addAllScreens(List<Screen> screensList) {
        screensList.forEach(screen -> {
            if (!screens.contains(screen)) {
                screens.add(screen);
                fireTableRowsInserted(screens.size() - 1, screens.size() - 1);
            }
        });
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
        fireTableRowsInserted(screens.size() - 1, screens.size() - 1);
    }


    public void deleteScreen(int rowIndex) {
        screens.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Screen getScreen(int i) {
        return screens.get(i);
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
            case 3://Service tag
                return String.class;
            case 4://purchase date
                return Date.class;
            case 5://serialized properties
                return String.class;
            case 6://computer
                return Computer.class;
            default:
                return Object.class;
        }
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }
}