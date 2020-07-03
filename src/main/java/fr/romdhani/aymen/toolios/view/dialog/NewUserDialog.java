package fr.romdhani.aymen.toolios.view.dialog;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class NewUserDialog extends JDialog {
    private Consumer<UserAccount> userAccountConsumerValid = a -> {
    };
    private Consumer<UserAccount> userAccountConsumerCancel = a -> {
    };
    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");
    JTextField emailTextField = new JTextField();

    public NewUserDialog() {
        super();
        initComponents();
    }

    public Consumer<UserAccount> getUserAccountConsumerValid() {
        return userAccountConsumerValid;
    }

    public void setUserAccountConsumerValid(Consumer<UserAccount> userAccountConsumerValid) {
        this.userAccountConsumerValid = userAccountConsumerValid;
    }

    public Consumer<UserAccount> getUserAccountConsumerCancel() {
        return userAccountConsumerCancel;
    }

    public void setUserAccountConsumerCancel(Consumer<UserAccount> userAccountConsumerCancel) {
        this.userAccountConsumerCancel = userAccountConsumerCancel;
    }

    private void initComponents() {
        setSize(800, 400);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel fName = new JLabel("First name: ");
        JTextField fNameTextField = new JTextField();
        JLabel lName = new JLabel("Last name: ");
        JTextField lNameTextField = new JTextField();
        JLabel loginLabel = new JLabel("Login: ");
        JTextField loginTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");

        emailTextField.addActionListener(e -> {
            checkEmail(emailTextField.getText());
        });
        JLabel phoneLabel = new JLabel("Phone number: ");
        JTextField phoneTextField = new JTextField();
        JLabel creationLabel = new JLabel("Creation mode: ");
        JTextField creationTextField = new JTextField();
        JLabel streetLabel = new JLabel("Street: ");
        JTextField streetTextField = new JTextField();
        JLabel cityLabel = new JLabel("City: ");
        JTextField cityTextField = new JTextField();
        JLabel codeLabel = new JLabel("Code: ");
        JTextField codeTextField = new JTextField();
        JLabel countryLabel = new JLabel("Country: ");
        JTextField countryTextField = new JTextField();
        JLabel functionLabel = new JLabel("Function: ");
        JTextField functionTextField = new JTextField();
        JLabel rolesLabel = new JLabel("Roles: ");
        JTextField rolesTextField = new JTextField();
        JLabel groupLabel = new JLabel("Group: ");
        JTextField groupTextField = new JTextField();

        userPanel.add(fName);
        userPanel.add(fNameTextField, "grow,push, wrap");

        userPanel.add(lName);
        userPanel.add(lNameTextField, "grow,push, wrap");

        userPanel.add(loginLabel);
        userPanel.add(loginTextField, "grow,push, wrap");

        userPanel.add(emailLabel);
        userPanel.add(emailTextField, "grow,push, wrap");

        userPanel.add(phoneLabel);
        userPanel.add(phoneTextField, "grow,push, wrap");

        userPanel.add(creationLabel);
        userPanel.add(creationTextField, "grow,push, wrap ");
        //Adress
        userPanel.add(new JLabel("Address"), "grow,push, wrap ");

        userPanel.add(streetLabel);
        userPanel.add(streetTextField, "grow,push, wrap");

        userPanel.add(cityLabel);
        userPanel.add(cityTextField, "grow,push, wrap");

        userPanel.add(codeLabel);
        userPanel.add(codeTextField, "grow,push, wrap");

        userPanel.add(countryLabel);
        userPanel.add(countryTextField, "grow,push, wrap");

        userPanel.add(new JLabel(), "grow,push, wrap ");
        //
        userPanel.add(functionLabel);
        userPanel.add(functionTextField, "grow,push, wrap");

        userPanel.add(rolesLabel);
        userPanel.add(rolesTextField, "grow,push, wrap");

        userPanel.add(groupLabel);
        userPanel.add(groupTextField, "grow,push, wrap");
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userAccountConsumerCancel.accept(null);
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {

            }
        });
        addButton.addActionListener(e -> {
            String firstName = fNameTextField.getText();
            String lastName = lNameTextField.getText();
            String login = loginTextField.getText();
            String email = emailLabel.getText();
            String phone = phoneTextField.getText();
            String creationMode = creationTextField.getText();
            String address = streetTextField.getText();
            String roles = rolesTextField.getText();
            String group = groupTextField.getText();
            UserAccount userAccount = new UserAccount();
            userAccount.setF_name(firstName);
            userAccount.setL_name(lastName);
            userAccount.setLogin(login);
            userAccount.setEmail(email);
            userAccount.setPhone_number(phone);
            userAccount.setCreation_mode(creationMode);
            Address adress = new Address();
            adress.setStreet(streetTextField.getText());
            adress.setCity(cityTextField.getText());
            adress.setCode(codeTextField.getText());
            adress.setCountry(countryTextField.getText());
            userAccount.setAddress(adress);
            userAccount.setCreation_mode(creationMode);
            userAccount.setCreation_mode(creationMode);
            userAccountConsumerValid.accept(userAccount);
        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(cancelButton);
        add(userPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);

    }

    private void checkEmail(String text) {
        if (text.matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}") || emailTextField.getText().trim().isEmpty()) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
    }

    private void cancelActionPerofrmed() {
        this.dispose();
    }
}
