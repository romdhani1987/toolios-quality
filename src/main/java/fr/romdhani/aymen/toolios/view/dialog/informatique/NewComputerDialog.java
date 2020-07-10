package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import fr.romdhani.aymen.toolios.view.commons.DateLabelFormatter;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;
import java.util.function.Supplier;

public class NewComputerDialog extends JDialog {

    private static final String osWindows = "Windows";
    private static final String osUnix = "Unix";
    private static final String osSolaris = "Solaris";
    private static final String osMac = "Macintos";
    private static final String osOther = "Other";
    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField computerNameTextField = new JTextField();
    private JTextField serialNumberTextField = new JTextField();
    private JTextField processorTextField = new JTextField();
    private JTextField ramTextField = new JTextField();
    private JTextField serviceTagTextField = new JTextField();
    private JComboBox<String> osComboBox = new JComboBox();
    private JTextField ageField = new JTextField();
    private JCheckBox isShiftingCkBoc = new JCheckBox();
    private JTextField userTextField = new JTextField();
    private JTextField screensTextField = new JTextField();
    private JTextField licensesTextField = new JTextField();
    private UtilDateModel model = new UtilDateModel();
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
        setSize(600, 500);
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

        userPanel.add(computerNameLabel);
        userPanel.add(computerNameTextField, "grow,push, wrap");
        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "grow,push, wrap");

        userPanel.add(processorLabel);
        userPanel.add(processorTextField, "grow,push, wrap");

        userPanel.add(ramLabel);
        userPanel.add(ramTextField, "grow,push, wrap");

        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "grow,push, wrap");

        userPanel.add(osLabel);
        osComboBox.addItem(osWindows);
        osComboBox.addItem(osUnix);
        osComboBox.addItem(osMac);
        osComboBox.addItem(osSolaris);
        osComboBox.addItem(osOther);
        userPanel.add(osComboBox, "grow,push, wrap ");

        userPanel.add(ageLabel);
        ageField.setEditable(false);
        userPanel.add(ageField, "grow,push, wrap");

        userPanel.add(shiftingLabel);
        userPanel.add(isShiftingCkBoc, "grow,push, wrap");

        userPanel.add(purchasingDateLabel);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        userPanel.add(datePicker, "grow,push, wrap");

        userPanel.add(licensesLabel);
        userPanel.add(licensesTextField, "grow,push, wrap");

        userPanel.add(userLabel);
        userPanel.add(userTextField, "grow,push, wrap");

        userPanel.add(screensLabel);
        userPanel.add(screensTextField, "grow,push, wrap");

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
        addButton.addActionListener(e -> {
            addComputer();

        });
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addButton);
        footerPanel.add(cancelButton);
        add(userPanel, BorderLayout.CENTER);
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
            computerSupplierValid = () -> {
                return computer;
            };
            this.dispose();
        }
    }

    private int computeAge(LocalDate today, LocalDate current) {
        if ((today != null) && (current != null)) {
            return Period.between(today, current).getYears();
        } else {
            return 0;
        }
    }
}
