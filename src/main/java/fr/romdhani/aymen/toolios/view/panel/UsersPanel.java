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
        updateButton = new TooliosButton("Update");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        this.add(new JScrollPane(usersTable), "grow,span, push");
        this.add(buttonsPanel, "grow,span,push");
    }

    private void addUser() {
        NewUserDialog userDialog = new NewUserDialog();
        userDialog.setModal(true);
        userDialog.setLocationRelativeTo(null);
        userDialog.setVisible(true);
        if (userDialog.getUserAccountSupplierValid().get() != null) {
            UserAccount user = userDialog.getUserAccountSupplierValid().get();
            System.out.println(user.toString());
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
