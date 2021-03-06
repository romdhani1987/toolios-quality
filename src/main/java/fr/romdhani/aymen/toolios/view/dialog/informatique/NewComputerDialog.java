package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.controller.utils.DatabaseUtils;
import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.UserAccount;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

public class NewComputerDialog extends JDialog {

    private static final String OS_WINDOWS = "Windows";
    private static final String OS_UNIX = "Unix";
    private static final String OS_SOLARIS = "Solaris";
    private static final String OS_MAC = "Macintos";
    private static final String OS_OTHER = "Other";
    private static final String ERROR = "Error occurred: maybe some fields are incorrect!";
    private JButton addButton = new JButton(" Add ");
    private JButton clearButton = new JButton("Clear");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField computerNameTextField = new JTextField();
    private JTextField serialNumberTextField = new JTextField();
    private JTextField processorTextField = new JTextField();
    private JTextField ramTextField = new JTextField();
    private JTextField serviceTagTextField = new JTextField();
    private JComboBox<String> osComboBox = new JComboBox();
    private JTextField ageField = new JTextField();
    private JCheckBox isShiftingCkBoc = new JCheckBox();
    private JComboBox<UserAccount> userComboBox = new JComboBox();
    private JTextField screensTextField = new JTextField();
    private JTextField licensesTextField = new JTextField();
    private UtilDateModel model = new UtilDateModel();
    private JLabel errorLabel;
    private Computer computer;
    private Supplier<Computer> computerSupplierValid = () -> {
        return computer;
    };
    private Supplier<Computer> computerSupplierCancel = () -> {
        return computer;
    };

    public NewComputerDialog() {
        super();
        initComponents();
    }

    public Supplier<Computer> getComputerSupplierValid() {
        return computerSupplierValid;
    }

    public void setComputerSupplierValid(Supplier<Computer> computerSupplierValid) {
        this.computerSupplierValid = computerSupplierValid;
    }

    public Supplier<Computer> getComputerSupplierCancel() {
        return computerSupplierCancel;
    }

    public void setComputerSupplierCancel(Supplier<Computer> computerSupplierCancel) {
        this.computerSupplierCancel = computerSupplierCancel;
    }

    private void initComponents() {
        setSize(800, 600);
        setTitle("New Computer");
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel computerNameLabel = new JLabel("Computer name * ");
        JLabel serialNumberLabel = new JLabel("Serial number * ");
        JLabel processorLabel = new JLabel("Processor * ");
        JLabel ramLabel = new JLabel("Ram * ");
        JLabel serviceLabel = new JLabel("Service tag * ");
        JLabel osLabel = new JLabel("Os ");
        JLabel ageLabel = new JLabel("Age ");
        JLabel shiftingLabel = new JLabel("Shifting ");
        JLabel purchasingDateLabel = new JLabel("Purchase date ");
        JLabel userLabel = new JLabel("User ");
        JLabel screensLabel = new JLabel("Screens ");
        JLabel licensesLabel = new JLabel("Licenses ");
        // Layout
        userPanel.add(computerNameLabel);
        userPanel.add(computerNameTextField, "growx,push, wrap");
        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "growx,push, wrap");

        userPanel.add(processorLabel);
        userPanel.add(processorTextField, "growx,push, wrap");

        userPanel.add(ramLabel);
        userPanel.add(ramTextField, "growx,push, wrap");

        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "growx,push, wrap");

        userPanel.add(osLabel);
        osComboBox.addItem(OS_WINDOWS);
        osComboBox.addItem(OS_UNIX);
        osComboBox.addItem(OS_MAC);
        osComboBox.addItem(OS_SOLARIS);
        osComboBox.addItem(OS_OTHER);
        userPanel.add(osComboBox, "growx,push, wrap ");

        userPanel.add(ageLabel);
        ageField.setEditable(false);
        userPanel.add(ageField, "growx,push, wrap");

        userPanel.add(shiftingLabel);
        userPanel.add(isShiftingCkBoc, "growx,push, wrap");

        userPanel.add(purchasingDateLabel);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        userPanel.add(datePicker, "grow, push, wrap");

        userPanel.add(userLabel);
        DatabaseUtils.getInstance().getUsers().forEach(user -> {
            userComboBox.addItem(user);
        });
      //  DatabaseUtils.getInstance().populateGroup();
        userPanel.add(userComboBox, "growx, push, wrap");

        userPanel.add(licensesLabel);
        userPanel.add(licensesTextField, "growx, push, wrap");

        userPanel.add(screensLabel);
        userPanel.add(screensTextField, "growx, push, wrap");

        clearButton.setIcon(IconResource.getImage(IconResource.ICON.ARROW_CIRCLE));
        clearButton.addActionListener(e -> {
            clear();
        });

        cancelButton.setIcon(IconResource.getImage(IconResource.ICON.CROSS));
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                computerSupplierCancel = () -> {
                    return (null);
                };
                this.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
            }
        });

        addButton.setIcon(IconResource.getImage(IconResource.ICON.TICK));
        addButton.addActionListener(e -> {
            addComputer();

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
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
    }

    private void addComputer() {
        if (!StringUtils.isNullOrEmpty(computerNameTextField.getText()) && !StringUtils.isNullOrEmpty(serialNumberTextField.getText())
                && !StringUtils.isNullOrEmpty(ramTextField.getText()) && !StringUtils.isNullOrEmpty(processorTextField.getText())
                && !StringUtils.isNullOrEmpty(serviceTagTextField.getText())) {
            String computerName = computerNameTextField.getText();
            String serial = serialNumberTextField.getText();
            String ram = ramTextField.getText();
            String processor = processorTextField.getText();
            String serviceTag = serviceTagTextField.getText();
            String os = (String) osComboBox.getSelectedItem();
            boolean isShifting = isShiftingCkBoc.isSelected();
            Timestamp creationTimestamp = new Timestamp(model.getValue().getTime());
            int age = computeAge(creationTimestamp.toLocalDateTime().toLocalDate(), LocalDate.now());
            computer = new Computer();
            computer.setName(computerName);
            computer.setSerialNumber(serial);
            computer.setRam(ram);
            computer.setProcessor(processor);
            computer.setServiceTag(serviceTag);
            computer.setAge(age);
            computer.setOs(os);
            computer.setShifting(isShifting);
            computer.setPurchaseDate(creationTimestamp);
           /* List<License> licenseList = new ArrayList<>();
            License license = new License();
            license.setName("Windows licenses");
            License license1 = new License();
            license1.setName("unix licenses");
            License license2 = new License();
            license2.setName("team licenses");
            Session session = getSession();
            Transaction tr = session.getTransaction();
            tr.begin();
            session.save(license);
            session.save(license1);
            session.save(license2);
            tr.commit();
            licenseList.add(license);
            computer.setLicenses(licenseList);*/

            errorLabel.setVisible(false);
            computerSupplierValid = () -> {
                return computer;
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
        componentList.add(computerNameTextField);
        componentList.add(serialNumberTextField);
        componentList.add(processorTextField);
        componentList.add(ramTextField);
        componentList.add(serviceTagTextField);
        componentList.add(ageField);
        componentList.add(computerNameTextField);
        componentList.add(computerNameTextField);
        componentList.add(computerNameTextField);
        componentList.add(computerNameTextField);
        componentList.forEach(comp -> comp.setText(""));
    }

    private int computeAge(LocalDate today, LocalDate current) {
        if ((today != null) && (current != null)) {
            return Period.between(today, current).getYears();
        } else {
            return 0;
        }
    }
}
