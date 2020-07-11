package fr.romdhani.aymen.toolios.view.dialog.user;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.Hash;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class NewUserDialog extends JDialog {

    private JButton addButton = new JButton(" Add ");
    private JButton clearButton = new JButton("Clear");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField emailTextField = new JTextField();
    private JTextField fNameTextField = new JTextField();
    private JTextField lNameTextField = new JTextField();
    private JTextField loginTextField = new JTextField();
    private JPasswordField pass1Field = new JPasswordField();
    private JPasswordField pass2Field = new JPasswordField();
    private JTextField phoneTextField = new JTextField();
    private JComboBox<String> creationCBox = new JComboBox();
    private JTextField streetTextField = new JTextField();
    private JTextField cityTextField = new JTextField();
    private JTextField codeTextField = new JTextField();
    private JTextField countryTextField = new JTextField();
    private JTextField functionTextField = new JTextField();
    private JTextField rolesTextField = new JTextField();
    private JTextField groupTextField = new JTextField();
    private Supplier<UserAccount> userAccountSupplierCancel;
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
        setSize(800, 600);
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

        creationCBox.addItem("MANUAL");
        creationCBox.addItem("AUTOMATIQUE");
        userPanel.add(creationLabel);
        userPanel.add(creationCBox, "grow,push, wrap ");
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
        clearButton.addActionListener(e -> {
            clearFields();
        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(clearButton);
        footerPanel.add(cancelButton);
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void clearFields() {
        List<JTextComponent> list = new ArrayList<>();
        list.add(fNameTextField);
        list.add(lNameTextField);
        list.add(loginTextField);
        list.add(pass1Field);
        list.add(pass2Field);
        list.add(emailTextField);
        list.add(phoneTextField);
        list.add(streetTextField);
        list.add(codeTextField);
        list.add(cityTextField);
        list.add(countryTextField);
        list.forEach(component -> component.setText(""));
        creationCBox.setSelectedItem(creationCBox.getItemAt(0));
    }

    private void addUser() throws NoSuchAlgorithmException {
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        String login = loginTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String creationMode = (String) creationCBox.getSelectedItem();

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


}