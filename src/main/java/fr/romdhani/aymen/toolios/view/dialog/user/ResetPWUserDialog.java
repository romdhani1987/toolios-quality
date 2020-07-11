package fr.romdhani.aymen.toolios.view.dialog.user;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.Hash;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ResetPWUserDialog extends JDialog {

    private JButton applyButton = new JButton("Apply");
    private JButton clearButton = new JButton("Clear");
    private JButton cancelButton = new JButton("Cancel");

    private JTextField loginTextField = new JTextField();
    private JPasswordField oldPassField = new JPasswordField();
    private JPasswordField pass1Field = new JPasswordField();
    private JPasswordField pass2Field = new JPasswordField();
    private UserAccount userAccount = null;
    private Supplier<UserAccount> userAccountSupplierCancel = () -> {
        return userAccount;
    };
    private Supplier<UserAccount> userAccountSupplierValid = () -> {
        return null;
    };

    public Supplier<UserAccount> getUserAccountSupplierValid() {
        return userAccountSupplierValid;
    }

    public ResetPWUserDialog(UserAccount userAccount) {
        super();
        this.userAccount = userAccount;
        initComponents();
    }


    private void initComponents() {
        setSize(800, 200);
        setTitle("Reset Password");
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel loginLabel = new JLabel("Login * ");
        JLabel oldPassLabel = new JLabel("Old password * ");
        JLabel pass1Label = new JLabel("New password * ");
        JLabel pass2Label = new JLabel("Confirm password * ");


        userPanel.add(loginLabel);
        loginTextField.setEditable(false);
        loginTextField.setText(userAccount.getLogin());
        userPanel.add(loginTextField, "grow,push, wrap");

        userPanel.add(oldPassLabel);
        userPanel.add(oldPassField, "grow,push, wrap");

        userPanel.add(pass1Label);
        userPanel.add(pass1Field, "grow,push, wrap");

        userPanel.add(pass2Label);
        userPanel.add(pass2Field, "grow,push, wrap");


        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want really to cancel?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userAccount = null;
                this.dispose();
            }
        });
        applyButton.addActionListener(e -> {
            try {
                applyChanges();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        });
        clearButton.addActionListener(e -> {
            clearFields();
        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(applyButton);
        footerPanel.add(clearButton);
        footerPanel.add(cancelButton);
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void clearFields() {
        List<JTextComponent> list = new ArrayList<>();
        list.add(oldPassField);
        list.add(pass1Field);
        list.add(pass2Field);
        list.forEach(component -> component.setText(""));
    }

    private void applyChanges() throws NoSuchAlgorithmException {
        if (isValidPass(pass1Field.getPassword()) && isValidPass(pass1Field.getPassword()) && samePass()) {
            userAccount.setPasswordHash(getHash());
            userAccountSupplierValid = () -> {
                return (userAccount);
            };
            this.dispose();
        } else {
            System.err.println("Reset password has failed!");
        }
    }

    public boolean isValidPass(char[] seq) {
        return seq.length >= 8;
    }

    private boolean samePass() {
        return Arrays.equals(pass1Field.getPassword(), pass1Field.getPassword());
    }

    private String getHash() {
        return Hash.asIsoString(Hash.sha256(pass1Field.getText() + "toolios"));
    }
}