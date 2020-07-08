package fr.romdhani.aymen.toolios.view.dialog;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.Hash;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.function.Supplier;

public class NewUserDialog extends JDialog {

    private Supplier<UserAccount> userAccountSupplierCancel;
    JButton addButton = new JButton(" Add ");
    JButton cancelButton = new JButton("Cancel");
    JTextField emailTextField = new JTextField();
    JTextField fNameTextField = new JTextField();
    JTextField lNameTextField = new JTextField();
    JTextField loginTextField = new JTextField();
    JPasswordField pass1Field = new JPasswordField();
    JPasswordField pass2Field = new JPasswordField();
    JTextField phoneTextField = new JTextField();
    JTextField creationTextField = new JTextField();
    JTextField streetTextField = new JTextField();
    JTextField cityTextField = new JTextField();
    JTextField codeTextField = new JTextField();
    JTextField countryTextField = new JTextField();
    JTextField functionTextField = new JTextField();
    JTextField rolesTextField = new JTextField();
    JTextField groupTextField = new JTextField();
    private byte[] passwordBytes;
    private UserAccount userAccount = null;
    private Supplier<UserAccount> userAccountSupplierValid = () -> {
        return userAccount;
    };

    public NewUserDialog() {
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
        setSize(750, 500);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel fName = new JLabel("First name * ");
        JLabel lName = new JLabel("Last name * ");
        JLabel loginLabel = new JLabel("Login * ");
        JLabel emailLabel = new JLabel("Email * ");
        JLabel pass1Label = new JLabel("Password * ");
        JLabel pass2Label = new JLabel("Confirm Password * ");
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

        userPanel.add(lName);
        userPanel.add(lNameTextField, "grow,push, wrap");

        userPanel.add(loginLabel);
        userPanel.add(loginTextField, "grow,push, wrap");

        userPanel.add(pass1Label);
        userPanel.add(pass1Field, "grow,push, wrap");

        userPanel.add(pass2Label);
        userPanel.add(pass2Field, "grow,push, wrap");

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
                userAccountSupplierCancel = () -> {
                    return (null);
                };
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });
        addButton.addActionListener(e -> {
            try {
                addUser();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(cancelButton);
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void addUser() throws NoSuchAlgorithmException {
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
        if (!StringUtils.isNullOrEmpty(firstName) &&
                !StringUtils.isNullOrEmpty(lastName) &&
                !StringUtils.isNullOrEmpty(login) && !StringUtils.isNullOrEmpty(email) &&
                isValidPass(pass1Field.getPassword()) && isValidPass(pass1Field.getPassword()) && samePass()) {
            userAccount = new UserAccount();
            userAccount.setFirstName(firstName);
            userAccount.setLastName(lastName);
            userAccount.setLogin(login);
            userAccount.setPasswordHash(getHash());
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
        } else {
            System.err.println("Error while trying to add a user");
        }
    }


    private boolean checkEmail() {
        if (!emailTextField.getText().matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}") || emailTextField.getText().trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPass(char[] seq) {
        return seq.length >= 8;
    }

    private boolean samePass() {
        return Arrays.equals(pass1Field.getPassword(), pass1Field.getPassword());
    }

    private void cancelActionPerofrmed() {
        this.dispose();
    }

    private String getHash() {
        return Hash.asIsoString(Hash.sha256(pass1Field.getText() + "toolios"));
    }

    public boolean checkPassword(char[] password) {
        String enteredPass = new String(password);
        String codePass = Hash.asIsoString(Hash.sha256(enteredPass + "toolios"));
        String storedPass = new String(passwordBytes, StandardCharsets.UTF_8);
        return codePass.equals(storedPass);
    }

}