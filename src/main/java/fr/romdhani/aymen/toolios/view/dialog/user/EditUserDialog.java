package fr.romdhani.aymen.toolios.view.dialog.user;

import fr.romdhani.aymen.toolios.controller.utils.DatabaseUtils;
import fr.romdhani.aymen.toolios.core.orm.*;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import fr.romdhani.aymen.toolios.view.utils.IconResource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Supplier;

/**
 * Creates and displays edit user dialog
 *
 * @author aromdhani
 */
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
    private JComboBox<UserFunction> functionComboBox = new JComboBox();
    private JComboBox<UserRoles> rolesComboBox = new JComboBox();
    private JComboBox<UserGroup> groupComboBox = new JComboBox();
    private JComboBox<Company> companyComboBox = new JComboBox();
    private JLabel errorLabel;
    private UserAccount userAccount = null;
    private Supplier<UserAccount> userAccountSupplierValid = () -> {
        return userAccount;
    };
    private Supplier<UserAccount> userAccountSupplierCancel = () -> {
        return null;
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
        JLabel rolesLabel = new JLabel("Role ");
        JLabel groupLabel = new JLabel("Group ");
        JLabel companyLabel = new JLabel("Company ");

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
        // User company
        userPanel.add(companyLabel);
        DatabaseUtils.getInstance().getUserCompany().forEach(company -> {
            companyComboBox.addItem(company);
        });
        if (userAccount != null && userAccount.getGroup() != null) {
            companyComboBox.setSelectedItem(userAccount.getGroup().getCompany());
        }
        userPanel.add(companyComboBox, "grow,push, wrap");
        // User groups
        userPanel.add(groupLabel);
        DatabaseUtils.getInstance().getUserGroups().forEach(group -> {
            groupComboBox.addItem(group);
        });
        if (userAccount != null && userAccount.getGroup() != null) {
            groupComboBox.setSelectedItem(userAccount.getGroup());
        }
        userPanel.add(groupComboBox, "grow,push, wrap");

        // User functions
        userPanel.add(functionLabel);
        DatabaseUtils.getInstance().getUserFunctions().forEach(function -> {
            functionComboBox.addItem(function);
        });
        if (userAccount != null && userAccount.getFunction() != null) {
            functionComboBox.setSelectedItem(userAccount.getFunction());
        }
        userPanel.add(functionComboBox, "grow,push, wrap");
        // User Roles
        userPanel.add(rolesLabel);
        DatabaseUtils.getInstance().getUserRoles().forEach(role -> {
            rolesComboBox.addItem(role);
        });
        if (userAccount != null && userAccount.getRoles() != null) {
            rolesComboBox.setSelectedItem(userAccount.getRoles());
        }
        userPanel.add(rolesComboBox, "grow,push, wrap");

        cancelButton.setIcon(IconResource.getImage(IconResource.ICON.CROSS));
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to save modification? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userAccount = null;
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });
        addButton.setIcon(IconResource.getImage(IconResource.ICON.TICK));
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int result = JOptionPane.showConfirmDialog(null, "Do you really want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    userAccount = null;
                    dispose();
                }
            }
        });
    }

    private void validUser() {
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        String login = loginTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String street = streetTextField.getText();
        String code = codeTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();

        if (!StringUtils.isNullOrEmpty(firstName) &&
                !StringUtils.isNullOrEmpty(lastName) &&
                !StringUtils.isNullOrEmpty(login) && !StringUtils.isNullOrEmpty(email)) {
            userAccount.setFirstName(firstName);
            userAccount.setLastName(lastName);
            userAccount.setLogin(login);
            userAccount.setEmail(email);
            userAccount.setPhoneNumber(phone);
            userAccount.setCreationMode((String) creationCBox.getSelectedItem());

            Address adress = new Address();
            adress.setStreet(street);
            adress.setCity(city);
            adress.setCode(code);
            adress.setCountry(country);

            userAccount.setAddress(adress);
            userAccount.setGroup((UserGroup) groupComboBox.getSelectedItem());
            userAccount.setFunction((UserFunction) functionComboBox.getSelectedItem());
            userAccount.setRoles((UserRoles) rolesComboBox.getSelectedItem());

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
