package fr.romdhani.aymen.toolios.view.dialog.user;

import fr.romdhani.aymen.toolios.core.orm.Address;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.function.Supplier;

public class EditUserDialog extends JDialog {

    private static final String ERROR_MESSAGE = "Error occurred! Maybe some required fields are empty!";
    private JButton addButton = new JButton("Apply");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField emailTextField = new JTextField();
    private JTextField fNameTextField = new JTextField();
    private JTextField lNameTextField = new JTextField();
    private JTextField loginTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JComboBox<String> creationCBox = new JComboBox();
    private JTextField streetTextField = new JTextField();
    private JTextField cityTextField = new JTextField();
    private JTextField codeTextField = new JTextField();
    private JTextField countryTextField = new JTextField();
    private JTextField functionTextField = new JTextField();
    private JTextField rolesTextField = new JTextField();
    private JTextField groupTextField = new JTextField();
    private JLabel errorLabel;
    private UserAccount userAccount = null;
    private Supplier<UserAccount> userAccountSupplierValid = () -> {
        return userAccount;
    };
    private Supplier<UserAccount> userAccountSupplierCancel = () -> {
        return userAccount;
    };

    public EditUserDialog(UserAccount userAccount) {
        super();
        this.userAccount = userAccount;
        initComponents();
    }

    public Supplier<UserAccount> getUserAccountSupplierValid() {
        return userAccountSupplierValid;
    }

    public Supplier<UserAccount> getUserAccountSupplierCancel() {
        return userAccountSupplierCancel;
    }

    private void initComponents() {
        setSize(800, 600);
        setTitle("Edit User");
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
        updateField(userAccount.getFirstName(), fNameTextField);

        userPanel.add(lName);
        updateField(userAccount.getLastName(), lNameTextField);
        userPanel.add(lNameTextField, "grow,push, wrap");

        userPanel.add(loginLabel);
        updateField(userAccount.getLogin(), loginTextField);
        userPanel.add(loginTextField, "grow,push, wrap");

        userPanel.add(emailLabel);
        updateField(userAccount.getEmail(), emailTextField);
        userPanel.add(emailTextField, "grow,push, wrap");

        userPanel.add(phoneLabel);
        updateField(userAccount.getPhoneNumber(), phoneTextField);
        userPanel.add(phoneTextField, "grow,push, wrap");

        userPanel.add(creationLabel);
        creationCBox.addItem("MANUAL");
        creationCBox.addItem("AUTOMATIQUE");
        creationCBox.setSelectedItem(userAccount.getCreationMode());
        userPanel.add(creationCBox, "grow,push, wrap ");
        //Address
        userPanel.add(new JLabel("Address"), "grow,push, wrap ");

        userPanel.add(streetLabel);
        if (userAccount.getAddress() != null)
            updateField(userAccount.getAddress().getStreet(), streetTextField);
        userPanel.add(streetTextField, "grow,push, wrap");

        userPanel.add(cityLabel);
        if (userAccount.getAddress() != null)
            updateField(userAccount.getAddress().getCity(), cityTextField);
        userPanel.add(cityTextField, "grow,push, wrap");

        userPanel.add(codeLabel);
        if (userAccount.getAddress() != null)
            updateField(userAccount.getAddress().getCode(), codeTextField);
        userPanel.add(codeTextField, "grow,push, wrap");

        userPanel.add(countryLabel);
        if (userAccount.getAddress() != null)
            updateField(userAccount.getAddress().getCountry(), countryTextField);
        userPanel.add(countryTextField, "grow,push, wrap");

        userPanel.add(new JLabel(), "grow,push, wrap ");
        //
        userPanel.add(functionLabel);
        userPanel.add(functionTextField, "grow,push, wrap");

        userPanel.add(rolesLabel);
        if (userAccount.getRoles() != null)
            updateField(userAccount.getRoles().getName(), rolesTextField);
        userPanel.add(rolesTextField, "grow,push, wrap");

        userPanel.add(groupLabel);
        if (userAccount.getGroup() != null)
            updateField(userAccount.getGroup().getName(), groupTextField);
        userPanel.add(groupTextField, "grow,push, wrap");
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to save modification? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userAccount = null;
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });
        addButton.addActionListener(e -> {
            validUser();
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        errorLabel = new JLabel(ERROR_MESSAGE);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.RED);
        errorPanel.add(errorLabel);
        JPanel footPanel = new JPanel(new MigLayout());
        footPanel.add(errorPanel, "span 2,growx,push,wrap");
        footPanel.add(buttonsPanel, "span 2,growx,push,wrap");
        buttonsPanel.add(addButton);
        buttonsPanel.add(cancelButton);
        add(userPanel, BorderLayout.CENTER);
        add(footPanel, BorderLayout.PAGE_END);
    }

    private void validUser() {
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
                !StringUtils.isNullOrEmpty(login) && !StringUtils.isNullOrEmpty(email)) {
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
        } else {
            errorLabel.setVisible(true);
            System.err.println("Error while trying to add a user");
        }
    }

    private void checkFields() {
        addButton.setEnabled(!(fNameTextField.getText().isEmpty() || loginTextField.getText().isEmpty()
                || lNameTextField.getText().isEmpty() || emailTextField.getText().isEmpty()));
    }

    private void updateField(Object user, JTextComponent component) {
        if (user != null) {
            component.setText((String) user);
        } else {
            component.setText("");
        }
    }
}
