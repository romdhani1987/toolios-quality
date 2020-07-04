package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserModelObject extends AbstractTableModel {
    private final List<UserAccount> users = new ArrayList<UserAccount>();

    private final String[] header = {"ID", "login", "First name", "Last name", "Email", "Phone number", "creation mode", "Adress", "Function", "Roles", "Group"};

    public UserModelObject() {
        super();
    }

    public int getRowCount() {
        return users.size();
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
                return users.get(rowIndex).getId();
            case 1:
                return users.get(rowIndex).getLogin();
            case 2:
                return users.get(rowIndex).getFirstName();
            case 3:
                return users.get(rowIndex).getLastName();
            case 4:
                return users.get(rowIndex).getEmail();
            case 5:
                return users.get(rowIndex).getPhoneNumber();
            case 6:
                return users.get(rowIndex).getCreationMode();
            case 7:
                return users.get(rowIndex).getAddress();
            case 8:
                return users.get(rowIndex).getFunction();
            case 9:
                return users.get(rowIndex).getRoles();
            case 10:
                return users.get(rowIndex).getGroup();
            default:
                return null;
        }
    }

    public void addAllUsers(List<UserAccount> userAccountList) {
        userAccountList.forEach(user -> {
            if (!users.contains(user)) {
                users.add(user);
                fireTableRowsInserted(users.size() - 1, users.size() - 1);
            }
        });
    }

    public void addUser(UserAccount userAccount) {
        users.add(userAccount);
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    public void removeUser(int rowIndex) {
        users.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public UserAccount getUser(int i) {
        return users.get(i);
    }
}