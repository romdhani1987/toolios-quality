package fr.romdhani.aymen.toolios.view.table.cells;


import fr.romdhani.aymen.toolios.core.orm.Computer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

public class ComputerCellRenderer extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private Computer computer;
    private JComboBox comboBox;

    public ComputerCellRenderer() {
        super();
        comboBox = new JComboBox();
        java.util.List<Computer> computers = (List<Computer>) getSession().createQuery("from Computer").list();
        computers.forEach(e -> {
            comboBox.addItem(e.getName());
        });
        comboBox.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
    }

    @Override
    public Object getCellEditorValue() {
        return computer;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        computer = (Computer) value;
        return comboBox;
    }
}