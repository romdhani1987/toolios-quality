package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.utils.StringUtils;
import fr.romdhani.aymen.toolios.view.commons.DateLabelFormatter;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

public class EditComputerDialog extends JDialog {

    private static final String OS_WINDOWS = "Windows";
    private static final String OS_UNIX = "Unix";
    private static final String OS_SOLARIS = "Solaris";
    private static final String OS_MAC = "Macintos";
    private static final String OS_OTHER = "Other";
    private static final String ERROR = "Error occurred: maybe some fields are incorrect!";
    private JButton applyChanges = new JButton(" Apply ");
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
    private JTextField userTextField = new JTextField();
    private JTextField screensTextField = new JTextField();
    private JTextField licensesTextField = new JTextField();
    private UtilDateModel model = new UtilDateModel();
    private JLabel errorLabel;
    private Computer computer;
    private Supplier<Computer> computerSupplierValid = () -> {
        return computer;
    };
    private Supplier<Computer> computerSupplierCancel = () -> {
        return null;
    };

    public EditComputerDialog(Computer computer) {
        super();
        this.computer = computer;
        initComponents();
    }

    public Supplier<Computer> getComputerSupplierValid() {
        return computerSupplierValid;
    }

    public Supplier<Computer> getComputerSupplierCancel() {
        return computerSupplierCancel;
    }

    private void initComponents() {
        setSize(800, 600);
        setTitle("Edit Computer");
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
        setInitialValue(computerNameTextField, computer.getName());

        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "growx,push, wrap");
        setInitialValue(serialNumberTextField, computer.getSerialNumber());


        userPanel.add(processorLabel);
        userPanel.add(processorTextField, "growx,push, wrap");
        setInitialValue(processorTextField, computer.getProcessor());

        userPanel.add(ramLabel);
        userPanel.add(ramTextField, "growx,push, wrap");
        setInitialValue(ramTextField, computer.getRam());


        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "growx,push, wrap");
        setInitialValue(serviceTagTextField, computer.getServiceTag());

        userPanel.add(osLabel);
        osComboBox.addItem(OS_WINDOWS);
        osComboBox.addItem(OS_UNIX);
        osComboBox.addItem(OS_MAC);
        osComboBox.addItem(OS_SOLARIS);
        osComboBox.addItem(OS_OTHER);
        userPanel.add(osComboBox, "growx,push, wrap ");
        if (computer != null && computer.getOs() != null) {
            osComboBox.setSelectedItem(computer.getOs());
        }

        userPanel.add(ageLabel);
        ageField.setEditable(false);
        userPanel.add(ageField, "growx,push, wrap");
        setInitialValue(ageField, String.valueOf(computer.getAge()));

        userPanel.add(shiftingLabel);
        userPanel.add(isShiftingCkBoc, "growx,push, wrap");
        if (computer != null) {
            isShiftingCkBoc.setSelected(computer.isShifting());
        }
        userPanel.add(purchasingDateLabel);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        userPanel.add(datePicker, "grow,push, wrap");
        if (computer.getPurchaseDate() != null) {
            Date changeDate = new Date(computer.getPurchaseDate().getTime());
            model.setDate(changeDate.getYear(), changeDate.getMonth(), changeDate.getDay());
        }
        userPanel.add(licensesLabel);
        userPanel.add(licensesTextField, "growx,push, wrap");

        userPanel.add(userLabel);
        userPanel.add(userTextField, "growx,push, wrap");

        userPanel.add(screensLabel);
        userPanel.add(screensTextField, "growx,push, wrap");
        clearButton.addActionListener(e -> {
            clear();
        });
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                computer = null;
                this.dispose();
            }
        });
        applyChanges.addActionListener(e -> {
            applyChanges();

        });
        JPanel footerPanel = new JPanel(new MigLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(applyChanges);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(cancelButton);
        errorLabel = new JLabel(ERROR);
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        footerPanel.add(errorLabel, "growx, center,  wrap");
        footerPanel.add(buttonsPanel, "growx, push");
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                computer = null;
            }
        });
    }

    private void setInitialValue(JTextComponent component, Object value) {
        if (computer != null && value != null) {
            component.setText((String) value);
        }
    }

    private void applyChanges() {
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
            computer.setName(computerName);
            computer.setSerialNumber(serial);
            computer.setRam(ram);
            computer.setProcessor(processor);
            computer.setServiceTag(serviceTag);
            computer.setOs(os);
            computer.setShifting(isShifting);
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
}
