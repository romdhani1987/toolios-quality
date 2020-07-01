package fr.romdhani.aymen.toolios.view.panel;


import fr.romdhani.aymen.toolios.view.buttons.TooliosButton;
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

    private void initComponents() {
        setLayout(new MigLayout());
        userModelObject = new UserModelObject();
        usersTable = new JTable(userModelObject);
        addButton = new TooliosButton("Add");
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

    public UsersPanel() {
        super();
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
