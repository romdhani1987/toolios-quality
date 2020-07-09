package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class NewScreenDialog extends JDialog {
    private Supplier<UserAccount> userAccountSupplierValid = () -> {
        return null;
    };
    private Supplier<UserAccount> userAccountSupplierCancel;
    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");
    JTextField emailTextField = new JTextField();
    JTextField fNameTextField = new JTextField();
    JTextField lNameTextField = new JTextField();
    JTextField loginTextField = new JTextField();
    JTextField phoneTextField = new JTextField();
    JTextField creationTextField = new JTextField();
    JTextField streetTextField = new JTextField();
    JTextField cityTextField = new JTextField();
    JTextField codeTextField = new JTextField();
    JTextField countryTextField = new JTextField();
    JTextField functionTextField = new JTextField();
    JTextField rolesTextField = new JTextField();
    JTextField groupTextField = new JTextField();

    public NewScreenDialog() {
        super();
        initComponents();
    }

    public Supplier<UserAccount> getUserAccountSupplierValid() {
        return userAccountSupplierValid;
    }

    public void setUserAccountSupplierValid(Supplier<UserAccount> userAccountSupplierValid) {
        this.userAccountSupplierValid = userAccountSupplierValid;
    }

    public Supplier<UserAccount> getUserAccountSupplierCancel() {
        return userAccountSupplierCancel;
    }

    public void setUserAccountSupplierCancel(Supplier<UserAccount> userAccountSupplierCancel) {
        this.userAccountSupplierCancel = userAccountSupplierCancel;
    }

    private void initComponents() {
        setSize(600, 500);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel fName = new JLabel("First name * ");
        JLabel lName = new JLabel("Last name * ");
        JLabel loginLabel = new JLabel("Login * ");
        JLabel emailLabel = new JLabel("Email * ");
        JLabel phoneLabel = new JLabel("Phone number ");
        JLabel creationLabel = new JLabel("Creation mode ");
        JLabel streetLabel = new JLabel("Street ");
        JLabel cityLabel = new JLabel("City ");
        JLabel codeLabel = new JLabel("Code ");
        JLabel countryLabel = new JLabel("Country ");
        JLabel functionLabel = new JLabel("Function ");
        JLabel rolesLabel = new JLabel("Roles ");
        JLabel groupLabel = new JLabel("Group ");

        userPanel.add(fName);
        userPanel.add(fNameTextField, "grow,push, wrap");
        fNameTextField.addActionListener(e -> {
            checkFields();
        });

        userPanel.add(lName);
        userPanel.add(lNameTextField, "grow,push, wrap");
        lNameTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(loginLabel);
        userPanel.add(loginTextField, "grow,push, wrap");
        loginTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(emailLabel);
        userPanel.add(emailTextField, "grow,push, wrap");
        emailTextField.addActionListener(e -> {
            checkFields();
        });
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
                userAccountSupplierCancel = () -> {
                    return (null);
                };
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });
        addButton.addActionListener(e -> {
            addUser();

        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(cancelButton);
        add(userPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void addUser() {
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        String login = loginTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String creationMode = creationTextField.getText();

        String street = streetTextField.getText();
        String code = codeTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();

        String roles = rolesTextField.getText();
        String group = groupTextField.getText();
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setLogin(login);
        userAccount.setEmail(email);
        userAccount.setPhoneNumber(phone);
        userAccount.setCreationMode(creationMode);

        Address adress = new Address();
        adress.setStreet(street);
        adress.setCity(city);
        adress.setCode(code);
        adress.setCountry(country);
        userAccount.setAddress(adress);
        //userAccount.setCreation_mode(creationMode);
        //userAccount.setGroup();
        //userAccount
        userAccountSupplierValid = () -> {
            return (userAccount);
        };
        this.dispose();
    }

    private void checkFields() {
        addButton.setEnabled(!(fNameTextField.getText().isEmpty() || loginTextField.getText().isEmpty()
                || lNameTextField.getText().isEmpty() || emailTextField.getText().isEmpty()));
    }

    private boolean checkEmail() {
        if (!emailTextField.getText().matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}") || emailTextField.getText().trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void cancelActionPerofrmed() {
        this.dispose();
    }
}
