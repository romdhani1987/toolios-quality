package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import fr.romdhani.aymen.toolios.view.commons.DateLabelFormatter;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

/**
 * Creates and displays license dialog
 *
 * @author aromdhani
 */
public class LicenseDialog extends JDialog {

    private static final String ERROR = "Error occurred: maybe some fields are incorrect!";
    private JButton addButton = new JButton("Apply");
    private JButton clearButton = new JButton("Clear");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField licenseNameTextField = new JTextField();
    private JTextField licenseTypeTextField = new JTextField();
    private JTextField serialNumberTextField = new JTextField();
    private JComboBox<Computer> computerComboBox = new JComboBox();
    private UtilDateModel model = new UtilDateModel();
    private JLabel errorLabel;
    private License license;
    private boolean isEditable = false;
    private Supplier<License> licenseSupplierValid = () -> {
        return license;
    };
    private Supplier<License> licenseSupplierCancel = () -> {
        return null;
    };

    public Supplier<License> getLicenseSupplierValid() {
        return licenseSupplierValid;
    }

    public Supplier<License> getLicenseSupplierCancel() {
        return licenseSupplierCancel;
    }

    public LicenseDialog() {
        super();
        initComponents();
    }

    public LicenseDialog(License license, boolean isEditable) {
        super();
        this.license = license;
        this.isEditable = isEditable;
        initComponents();
    }


    private void initComponents() {
        setSize(800, 600);
        setTitle("New License");
        JPanel licensePanel = new JPanel();
        licensePanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel licenseNameLabel = new JLabel("License name * ");
        JLabel licenseTypeLabel = new JLabel("License type * ");
        JLabel serialNumberLabel = new JLabel("Serial number * ");
        JLabel purchasingDateLabel = new JLabel("Purchase date *");
        JLabel expirationDateLabel = new JLabel("Expiration date *");
        JLabel computerLabel = new JLabel("Computer ");
        // Layout
        if (license != null && isEditable) {
            licenseNameTextField.setText(license.getName());
        }
        licensePanel.add(licenseNameLabel);
        licensePanel.add(licenseNameTextField, "growx,push, wrap");

        if (license != null && isEditable) {
            licenseTypeTextField.setText(license.getType());
        }
        licensePanel.add(licenseTypeLabel);
        licensePanel.add(licenseTypeTextField, "growx,push, wrap");

        if (license != null && isEditable) {
            serialNumberTextField.setText(license.getSerialNumber());
        }
        licensePanel.add(serialNumberLabel);
        licensePanel.add(serialNumberTextField, "growx,push, wrap");
        // Purchase date
        licensePanel.add(purchasingDateLabel);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        licensePanel.add(datePicker, "growx,push, wrap");
        // Purchase date
        licensePanel.add(expirationDateLabel);
        JDatePanelImpl expDatePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl expDatePicker = new JDatePickerImpl(expDatePanel, new DateLabelFormatter());
        licensePanel.add(expDatePicker, "growx,push, wrap");
        //Computer
        licensePanel.add(computerLabel);
        List<Computer> computers = (List<Computer>) getSession().createQuery("from Computer").list();
        computers.forEach(computer -> {
            computerComboBox.addItem(computer);
        });
        if (license != null && isEditable) {
            computerComboBox.setSelectedItem(license.getComputer());
        }
        licensePanel.add(computerComboBox, "growx,push, wrap");
        clearButton.addActionListener(e -> {
            clear();
        });
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                licenseSupplierCancel = () -> {
                    return (null);
                };
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });
        addButton.addActionListener(e -> {
            applyChanges();
        });
        JPanel footerPanel = new JPanel(new MigLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(cancelButton);
        errorLabel = new JLabel(ERROR);
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        footerPanel.add(errorLabel, "growx, center,  wrap");
        footerPanel.add(buttonsPanel, "growx, push");
        add(new JScrollPane(licensePanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void applyChanges() {
        if (!StringUtils.isNullOrEmpty(licenseNameTextField.getText())
                && !StringUtils.isNullOrEmpty(licenseTypeTextField.getText())
                && !StringUtils.isNullOrEmpty(serialNumberTextField.getText())) {
            String computerName = licenseNameTextField.getText();
            String computerType = licenseNameTextField.getText();
            String serial = serialNumberTextField.getText();
            Timestamp purchaseTimestamp = new Timestamp(model.getValue().getTime());
            Timestamp expirationTimestamp = new Timestamp(model.getValue().getTime());
            if (isEditable) {
                license.setName(computerName);
                license.setType(computerType);
                license.setSerialNumber(serial);
                license.setComputer((Computer) computerComboBox.getSelectedItem());
            } else {
                license = new License();
                license.setName(computerName);
                license.setType(computerType);
                license.setSerialNumber(serial);
                license.setPurchaseDate(purchaseTimestamp);
                license.setExpirationDate(expirationTimestamp);
                license.setComputer((Computer) computerComboBox.getSelectedItem());
            }
            errorLabel.setVisible(false);
            licenseSupplierValid = () -> {
                return license;
            };
            this.dispose();
        } else {
            errorLabel.setVisible(true);
        }
    }

    /**
     * Clear all fields
     */
    private void clear() {
        List<JTextComponent> componentList = new ArrayList<>();
        componentList.add(licenseNameTextField);
        componentList.add(licenseTypeTextField);
        componentList.add(serialNumberTextField);
        computerComboBox.setSelectedItem(computerComboBox.getItemAt(0));
        componentList.forEach(comp -> comp.setText(""));
    }
}
