package fr.romdhani.aymen.toolios.view.table.cells;

import fr.romdhani.aymen.toolios.core.orm.Computer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CellComputerEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private Computer country;
    private List<Computer> listCountry;

    public CellComputerEditor(List<Computer> listCountry) {
        this.listCountry = listCountry;
    }

    @Override
    public Object getCellEditorValue() {
        return this.country;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (value instanceof Computer) {
            this.country = (Computer) value;
        }

        JComboBox<Computer> comboCountry = new JComboBox<Computer>();

        for (Computer aCountry : listCountry) {
            comboCountry.addItem(aCountry);
        }

        comboCountry.setSelectedItem(country);
        comboCountry.addActionListener(this);

        if (isSelected) {
            comboCountry.setBackground(table.getSelectionBackground());
        } else {
            comboCountry.setBackground(table.getSelectionForeground());
        }
        return comboCountry;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<Computer> comboCountry = (JComboBox<Computer>) event.getSource();
        this.country = (Computer) comboCountry.getSelectedItem();
    }

}