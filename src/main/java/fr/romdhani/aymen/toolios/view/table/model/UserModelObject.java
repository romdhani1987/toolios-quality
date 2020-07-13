package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.core.orm.UserGroup;
import fr.romdhani.aymen.toolios.core.orm.UserRoles;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserModelObject extends AbstractTableModel {
    private final List<UserAccount> users = new ArrayList<UserAccount>();

    private final String[] header = {"Id", "Login", "First name", "Last name", "Email", "Phone number", "Creation mode", "Address", "Function", "Roles", "Group"};

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

    public void deleteUser(int rowIndex) {
        users.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public UserAccount getUser(int i) {
        return users.get(i);
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
                return String.class;
            case 7://Adress
                return Address.class;
            case 8://Function
                return Function.class;
            case 9://Roles
                return UserRoles.class;
            case 10://Group
                return UserGroup.class;
            default:
                return Object.class;
        }
    }
}