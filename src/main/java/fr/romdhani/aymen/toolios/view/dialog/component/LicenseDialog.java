package fr.romdhani.aymen.toolios.view.dialog.component;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.License;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import fr.romdhani.aymen.toolios.view.commons.DateLabelFormatter;
import fr.romdhani.aymen.toolios.view.utils.IconResource;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private UtilDateModel expModel = new UtilDateModel();
    private JLabel errorLabel;
    private Optional<License> licenseOpt = Optional.empty();
    private boolean isEditable = false;
    private Supplier<Optional<License>> licenseSupplierValid = () -> {
        return licenseOpt;
    };
    private Supplier<Optional<License>> licenseSupplierCancel = () -> {
        return Optional.empty();
    };

    public Supplier<Optional<License>> getLicenseSupplierValid() {
        return licenseSupplierValid;
    }

    public Supplier<Optional<License>> getLicenseSupplierCancel() {
        return licenseSupplierCancel;
    }

    public LicenseDialog() {
        super();
        initComponents();
    }

    public LicenseDialog(License license, boolean isEditable) {
        super();
        this.licenseOpt = Optional.ofNullable(license);
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
        if (licenseOpt.isPresent() && isEditable) {
            licenseNameTextField.setText(licenseOpt.get().getName());
        }
        licensePanel.add(licenseNameLabel);
        licensePanel.add(licenseNameTextField, "growx,push, wrap");

        if (licenseOpt.isPresent() && isEditable) {
            licenseTypeTextField.setText(licenseOpt.get().getType());
        }
        licensePanel.add(licenseTypeLabel);
        licensePanel.add(licenseTypeTextField, "growx,push, wrap");

        if (licenseOpt.isPresent() && isEditable) {
            serialNumberTextField.setText(licenseOpt.get().getSerialNumber());
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
        JDatePanelImpl expDatePanel = new JDatePanelImpl(expModel, p);
        JDatePickerImpl expDatePicker = new JDatePickerImpl(expDatePanel, new DateLabelFormatter());
        licensePanel.add(expDatePicker, "growx,push, wrap");
        //Computer
        licensePanel.add(computerLabel);
        List<Computer> computers = (List<Computer>) getSession().createQuery("from Computer").list();
        Computer unknown = new Computer();
        unknown.setName("Unassigned");
        computerComboBox.addItem(unknown);
        computers.forEach(computer -> {
            computerComboBox.addItem(computer);
        });
        if (licenseOpt.isPresent() && isEditable) {
            computerComboBox.setSelectedItem(licenseOpt.get().getComputer());
        }
        licensePanel.add(computerComboBox, "growx,push, wrap");
        //Buttons
        clearButton.setIcon(IconResource.getImage(IconResource.ICON.ARROW_CIRCLE));
        clearButton.addActionListener(e -> {
            clear();
        });
        cancelButton.setIcon(IconResource.getImage(IconResource.ICON.CROSS));
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                licenseOpt = Optional.empty();
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });

        addButton.setIcon(IconResource.getImage(IconResource.ICON.TICK));
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                licenseOpt = Optional.empty();
            }
        });
    }

    private void applyChanges() {
        if (!StringUtils.isNullOrEmpty(licenseNameTextField.getText())
                && !StringUtils.isNullOrEmpty(licenseTypeTextField.getText())
                && !StringUtils.isNullOrEmpty(serialNumberTextField.getText())) {
            String computerName = licenseNameTextField.getText();
            String computerType = licenseTypeTextField.getText();
            String serial = serialNumberTextField.getText();

            if (isEditable && licenseOpt.isEmpty()) {
                licenseOpt.get().setName(computerName);
                licenseOpt.get().setType(computerType);
                licenseOpt.get().setSerialNumber(serial);
                licenseOpt.get().setComputer((Computer) computerComboBox.getSelectedItem());
            } else {
                Timestamp purchaseTimestamp = new Timestamp(model.getValue().getTime());
                Timestamp expirationTimestamp = new Timestamp(expModel.getValue().getTime());
                licenseOpt = Optional.of(new License());
                licenseOpt.get().setName(computerName);
                licenseOpt.get().setType(computerType);
                licenseOpt.get().setSerialNumber(serial);
                licenseOpt.get().setPurchaseDate(purchaseTimestamp);
                licenseOpt.get().setExpirationDate(expirationTimestamp);
                licenseOpt.get().setComputer((Computer) computerComboBox.getSelectedItem());
            }
            errorLabel.setVisible(false);
            licenseSupplierValid = () -> {
                return licenseOpt;
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
