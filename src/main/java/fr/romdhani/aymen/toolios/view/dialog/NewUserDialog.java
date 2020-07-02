package fr.romdhani.aymen.toolios.view.dialog;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class NewUserDialog extends JDialog {
    public NewUserDialog() {
        super();
        initComponents();
    }

    private void initComponents() {
        setSize(800, 600);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[200]10[300]"));
        JLabel fName = new JLabel("First name: ");
        JTextField fNameTextField = new JTextField();
        JLabel lName = new JLabel("Last name: ");
        JTextField lNameTextField = new JTextField();
        JLabel loginLabel = new JLabel("Login: ");
        JTextField loginTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailTextField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone number: ");
        JTextField phoneTextField = new JTextField();
        JLabel creationLabel = new JLabel("Creation mode: ");
        JTextField creationTextField = new JTextField();
        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressTextField = new JTextField();
        JLabel functionLabel = new JLabel("Function: ");
        JTextField functionTextField = new JTextField();
        JLabel rolesLabel = new JLabel("Roles: ");
        JTextField rolesTextField = new JTextField();
        JLabel groupLabel = new JLabel("Group: ");
        JTextField groupTextField = new JTextField();

        userPanel.add(fName);
        userPanel.add(fNameTextField, "grow,wrap");

        userPanel.add(lName);
        userPanel.add(lNameTextField, "grow,wrap");

        userPanel.add(loginLabel);
        userPanel.add(loginTextField, "grow,wrap");

        userPanel.add(emailLabel);
        userPanel.add(emailTextField, "grow,wrap");

        userPanel.add(phoneLabel);
        userPanel.add(phoneTextField, "grow,wrap");

        userPanel.add(creationLabel);
        userPanel.add(creationTextField, "grow,wrap");

        userPanel.add(addressLabel);
        userPanel.add(addressTextField, "grow,wrap");

        userPanel.add(functionLabel);
        userPanel.add(functionTextField, "grow,wrap");

        userPanel.add(rolesLabel);
        userPanel.add(rolesTextField, "grow,wrap");

        userPanel.add(groupLabel);
        userPanel.add(groupTextField, "grow,wrap");

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add");
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(cancelButton);
        add(userPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);

    }
}
