package fr.romdhani.aymen.toolios.view.panel.user;


import fr.romdhani.aymen.toolios.controller.user.UserController;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
import fr.romdhani.aymen.toolios.view.dialog.user.EditUserDialog;
import fr.romdhani.aymen.toolios.view.dialog.user.NewUserDialog;
import fr.romdhani.aymen.toolios.view.dialog.user.ResetPWUserDialog;
import fr.romdhani.aymen.toolios.view.panel.TooliosView;
import fr.romdhani.aymen.toolios.view.table.model.UserModelObject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UsersPanel extends JPanel implements TooliosView {
    private JTable usersTable;
    private UserModelObject userModelObject;
    private TooliosButton addButton;
    private TooliosButton editButton;
    private TooliosButton removeButton;
    private TooliosButton resetButton;
    private TooliosButton refreshButton;
    private UserController userController;

    private void initComponents() {
        setLayout(new MigLayout());
        userModelObject = new UserModelObject();
        usersTable = new JTable(userModelObject);
        usersTable.setAutoCreateRowSorter(true);

        addButton = new TooliosButton("Add");
        addButton.addActionListener(e -> {
            addUser();
        });
        editButton = new TooliosButton("Edit");
        editButton.addActionListener(e -> {
            editUser();
        });
        removeButton = new TooliosButton("Delete");
        removeButton.addActionListener(e -> {
            deleteUser();
        });
        resetButton = new TooliosButton("Reset password");
        resetButton.addActionListener(e -> {
            resetPw();
        });
        refreshButton = new TooliosButton("Refresh");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(refreshButton);
        this.add(new JScrollPane(usersTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void resetPw() {
        int row = usersTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No user were selected!", "Reset Password", 1);
        } else {
            int modelRow = usersTable.convertRowIndexToModel(row);
            UserAccount user = userModelObject.getUser(modelRow);
            ResetPWUserDialog resetDialog = new ResetPWUserDialog(user);
            resetDialog.setModal(true);
            resetDialog.setLocationRelativeTo(null);
            resetDialog.setVisible(true);
            UserAccount editedUser = resetDialog.getUserAccountSupplierValid().get();
            if (editedUser != null && userController.addUserToDb(editedUser)) {
                JOptionPane.showMessageDialog(null, "The password has been reset successfully!", "Confirm", 2);
                userModelObject.fireTableDataChanged();
                usersTable.repaint();
            }
        }
    }

    private void editUser() {
        int row = usersTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No user were selected!", "Edit User", 1);
        } else {
            int modelRow = usersTable.convertRowIndexToModel(row);
            UserAccount user = userModelObject.getUser(modelRow);
            EditUserDialog editUserDialog = new EditUserDialog(user);
            editUserDialog.setModal(true);
            editUserDialog.setLocationRelativeTo(null);
            editUserDialog.setVisible(true);
            UserAccount editedUser = editUserDialog.getUserAccountSupplierValid().get();
            if (editedUser != null && userController.addUserToDb(editedUser)) {
                JOptionPane.showMessageDialog(null, "The use has been edited successfully!", "Confirm", 2);
                userModelObject.fireTableDataChanged();
                usersTable.repaint();
            }
        }
    }

    private void deleteUser() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want really to delete this user account?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int row = usersTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "No user were selected to delete!", "Delete user", 1);
            } else {
                int modelRow = usersTable.convertRowIndexToModel(row);
                UserAccount user = userModelObject.getUser(modelRow);
                userController.deleteUserFromDb(user);
                userModelObject.deleteUser(modelRow);
            }
        }
    }

    private void addUser() {
        NewUserDialog userDialog = new NewUserDialog();
        userDialog.setModal(true);
        userDialog.setLocationRelativeTo(null);
        userDialog.setVisible(true);
        if (userDialog.getUserAccountSupplierValid().get() != null) {
            UserAccount user = userDialog.getUserAccountSupplierValid().get();
            if (userController.addUserToDb(user)) {
                JOptionPane.showMessageDialog(null, "The user has been added successfully!", "Confirm", 2);
                userModelObject.addUser(user);
                userModelObject.fireTableDataChanged();
                usersTable.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the user!",
                        "New User", JOptionPane.ERROR_MESSAGE);
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
