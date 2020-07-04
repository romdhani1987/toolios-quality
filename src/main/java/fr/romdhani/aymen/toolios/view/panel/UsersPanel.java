package fr.romdhani.aymen.toolios.view.panel;


import fr.romdhani.aymen.toolios.controller.UserController;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.NewUserDialog;
import fr.romdhani.aymen.toolios.view.table.model.UserModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UsersPanel extends JPanel {
    private JTable usersTable;
    private UserModelObject userModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton updateButton;
    private UserController userController;

    private void initComponents() {
        setLayout(new MigLayout());
        userModelObject = new UserModelObject();
        usersTable = new JTable(userModelObject);
        addButton = new TooliosButton("Add");
        addButton.addActionListener(e -> {
            addUser();
        });
        editButton = new TooliosButton("Edit");
        removeButton = new TooliosButton("Remove");
        removeButton.addActionListener(e -> {
            deleteUser();
        });
        updateButton = new TooliosButton("Update");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        this.add(new JScrollPane(usersTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void deleteUser() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want really to delete this user account?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = usersTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No user were selected to remove!", "Remove user", 1);
            } else {
                int modelRow = usersTable.convertRowIndexToModel(row);
                UserAccount user = userModelObject.getUser(modelRow);
                System.out.println("user to remove : " + user);
                userController.deleteUserFromDb(user);
            }
        }
    }

    private void addUser() {
        NewUserDialog userDialog = new NewUserDialog();
        userDialog.setModal(true);
        userDialog.setLocationRelativeTo(null);
        userDialog.setVisible(true);
        JOptionPane d = new JOptionPane();
        if (userDialog.getUserAccountSupplierValid().get() != null) {
            UserAccount user = userDialog.getUserAccountSupplierValid().get();
            if (userController.addUserToDb(user)) {
                JOptionPane.showMessageDialog(null, "The use has been added successfully", "Confirm", 2);
            } else {
                d.showMessageDialog(this, "Failed to delete this user",
                        "Delete user", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public UsersPanel(UserController userController) {
        super();
        this.userController = userController;
        initComponents();
    }

    public JTable getUsersTable() {
        return usersTable;
    }

    public void setUsersTable(JTable usersTable) {
        this.usersTable = usersTable;
    }

    public UserModelObject getUserModelObject() {
        return userModelObject;
    }

    public void setUserModelObject(UserModelObject userModelObject) {
        this.userModelObject = userModelObject;
    }
}
