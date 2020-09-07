package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Creates and displays Provider account
 *
 * @author aromdhani
 */
public class ProviderModelObject extends AbstractTableModel {
    private final List<ProviderAccount> providerAccounts = new ArrayList<ProviderAccount>();

    private final String[] header = {"Id", "Login", "First name", "Last name", "Email", "Phone number"};

    public ProviderModelObject() {
        super();
    }

    public int getRowCount() {
        return providerAccounts.size();
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
                return providerAccounts.get(rowIndex).getId();
            case 1:
                return providerAccounts.get(rowIndex).getLogin();
            case 2:
                return providerAccounts.get(rowIndex).getF_name();
            case 3:
                return providerAccounts.get(rowIndex).getL_name();
            case 4:
                return providerAccounts.get(rowIndex).getEmail();
            case 5:
                return providerAccounts.get(rowIndex).getPhone_number();
            case 6:
                return providerAccounts.get(rowIndex).getAddress();
            default:
                return null;
        }
    }

    public void addAllProviders(List<ProviderAccount> providerAccountList) {
        providerAccountList.forEach(user -> {
            if (!providerAccounts.contains(user)) {
                providerAccounts.add(user);
                fireTableRowsInserted(providerAccounts.size() - 1, providerAccounts.size() - 1);
            }
        });
    }

    public void addProvider(ProviderAccount providerAccount) {
        providerAccounts.add(providerAccount);
        fireTableRowsInserted(providerAccounts.size() - 1, providerAccounts.size() - 1);
    }

    public void deleteUser(int rowIndex) {
        providerAccounts.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public ProviderAccount getUser(int i) {
        return providerAccounts.get(i);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0://id
                return Integer.class;
            case 1://login
                return String.class;
            case 2://fName
                return String.class;
            case 3://lname
                return String.class;
            case 4://email
                return String.class;
            case 5://phone
                return String.class;
            case 6://creation mode
                return Address.class;
            default:
                return Object.class;
        }
    }
}