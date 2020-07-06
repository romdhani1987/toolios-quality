package fr.romdhani.aymen.toolios.view.dialog;

import fr.romdhani.aymen.toolios.controller.UserController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ConnectionDialog extends JDialog {
    private JLabel login;
    private JLabel password;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton clearButton;

    private UserController userController;
    public ConnectionDialog(String title) {
        super();
        setTitle(title);
        initComponents();
    }

    private void initComponents() {
        login = new JLabel("Login: ");
        password = new JLabel("Password: ");
        loginTextField = new JTextField();
        passwordTextField = new JPasswordField();
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        JPanel panel = new JPanel(new MigLayout());
        panel.add(login);
        panel.add(loginTextField, "growx,push,wrap");
        panel.add(password);
        panel.add(passwordTextField, "growx,push,wrap");
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButtons.add(loginButton);
        panelButtons.add(clearButton);
        panel.add(panelButtons,"span 2");
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }
}
