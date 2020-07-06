package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Screen;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
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
        screensList.forEach(user -> {
            if (!screens.contains(user)) {
                screens.add(user);
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
}