package fr.romdhani.aymen.toolios.view.dialog.user;

import fr.romdhani.aymen.toolios.controller.user.UserController;
import fr.romdhani.aymen.toolios.controller.user.UserSession;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.Hash;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Supplier;

public class ConnectionDialog extends JDialog {
    private static final String ERROR_MESSAGE = "Incorrect login or password!";
    private JLabel errorMessage;
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
        errorMessage = new JLabel(ERROR_MESSAGE);
        errorMessage.setForeground(Color.red);
        errorMessage.setVisible(false);
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
        JPanel panelError = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelError.add(errorMessage);
        panel.add(panelError, "growx, span 2,wrap");
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
        try {
            userAccount = userController.findByLogin(loginTextField.getText());
            if (userAccount != null && checkPassword(passwordTextField.getPassword(), userAccount.getPasswordHash().getBytes(StandardCharsets.UTF_8))) {
                UserSession.getInstance().setCurrentLogin(Optional.ofNullable(userAccount.getLogin()));
                errorMessage.setVisible(false);
                System.out.println(userAccount.getLogin() + ": signed in successfully!");
                this.dispose();
            } else {
                errorMessage.setVisible(true);
                clearFields();
            }
        } catch (Exception e) {
            errorMessage.setVisible(true);
            clearFields();
            System.err.println("Error while trying to sign in: " + e);
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
