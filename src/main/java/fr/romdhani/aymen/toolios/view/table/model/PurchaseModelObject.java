package fr.romdhani.aymen.toolios.view.table.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Create and displays the purchase model objects
 *
 * @author aromdhani
 */
public class PurchaseModelObject extends AbstractTableModel {
    private final List<Object> Objects = new ArrayList<Object>();

    private final String[] header = {"Purchase Order Number", "Applicant", "N+1", "DAF", "Purchase Order Date", "Description", "Provider", "TTC", "Validated by DAF"};

    public PurchaseModelObject() {
        super();
    }

    public int getRowCount() {
        return Objects.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            default:
                return null;
        }
    }


}