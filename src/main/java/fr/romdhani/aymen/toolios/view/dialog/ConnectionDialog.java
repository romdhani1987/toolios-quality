package fr.romdhani.aymen.toolios.view.dialog;

import fr.romdhani.aymen.toolios.controller.UserController;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.Hash;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class ConnectionDialog extends JDialog {
    private JLabel login;
    private JLabel password;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton clearButton;
    private UserController userController;
    private UserAccount userAccount;
    private Supplier<UserAccount> cancelSupplier = () -> {
        return userAccount;
    };
    private Supplier<UserAccount> validSupplier = () -> {
        return userAccount;
    };

    public ConnectionDialog(String title, UserController userController) {
        super();
        setTitle(title);
        this.userController = userController;
        initComponents();
    }

    public Supplier<UserAccount> getCancelSupplier() {
        return cancelSupplier;
    }

    public Supplier<UserAccount> getValidSupplier() {
        return validSupplier;
    }

    private void initComponents() {
        login = new JLabel("Login: ");
        password = new JLabel("Password: ");
        loginTextField = new JTextField();
        passwordTextField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            login();
        });
        clearButton = new JButton("Cancel");
        clearButton.addActionListener(e -> {
            cancel();
        });
        JPanel panel = new JPanel(new MigLayout());
        panel.add(login);
        panel.add(loginTextField, "growx,push,wrap");
        panel.add(password);
        panel.add(passwordTextField, "growx,push,wrap");
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButtons.add(loginButton);
        panelButtons.add(clearButton);
        panel.add(panelButtons, "growx, span 2");
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void cancel() {
        userAccount = null;
        System.exit(0);
    }

    private void login() {
        userAccount = userController.findByLogin(loginTextField.getText());
        if (userAccount != null && checkPassword(passwordTextField.getPassword(), userAccount.getPasswordHash().getBytes(StandardCharsets.UTF_8))) {
            System.out.println(userAccount.getLogin() + ": signed in successfully!");
            this.dispose();
        }else {
            clearFields();
        }
    }

    private void clearFields() {
        loginTextField.setText("");
        passwordTextField.setText("");
    }

    public boolean checkPassword(char[] password, byte[] passwordBytes) {
        String enteredPass = new String(password);
        String codePass = Hash.asIsoString(Hash.sha256(enteredPass + "toolios"));
        String storedPass = new String(passwordBytes, StandardCharsets.UTF_8);
        return codePass.equals(storedPass);
    }

}
