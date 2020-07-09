package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.function.Supplier;

public class EditComputerDialog extends JDialog {
    private Supplier<Computer> computerSupplierValid = () -> {
        return null;
    };
    private Supplier<Computer> computerSupplierCancel;
    private JButton addButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField computerNameTextField = new JTextField();
    private JTextField serialNumberTextField = new JTextField();
    private JTextField processorTextField = new JTextField();
    private JTextField ramTextField = new JTextField();
    private JTextField serviceTagTextField = new JTextField();
    private JTextField osTextField = new JTextField();
    private JTextField ageTextField = new JTextField();
    private JCheckBox shiftingTextChbox = new JCheckBox();
    private JTextField purchasingDateTextField = new JTextField();
    private JTextField userTextField = new JTextField();
    private JTextField screensTextField = new JTextField();
    private JTextField licensesTextField = new JTextField();
    private Computer computer;

    public EditComputerDialog(Computer computer) {
        super();
        this.computer = computer;
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
        JLabel serviceLabel = new JLabel("Service tag ");
        JLabel osLabel = new JLabel("Os ");
        JLabel ageLabel = new JLabel("Age ");
        JLabel shiftingLabel = new JLabel("Shifting ");
        JLabel purchasingDateLabel = new JLabel("Purchase date ");
        JLabel userLabel = new JLabel("User ");
        JLabel screensLabel = new JLabel("Screens ");
        JLabel licensesLabel = new JLabel("Licenses ");

        userPanel.add(computerNameLabel);
        userPanel.add(computerNameTextField, "grow,push, wrap");

        updateField(computer.getName(), computerNameTextField);
        computerNameTextField.addActionListener(e -> {
            checkFields();
        });

        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "grow,push, wrap");
        updateField(computer.getSerialNumber(), serialNumberTextField);
        serialNumberTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(processorLabel);
        userPanel.add(processorTextField, "grow,push, wrap");
        updateField(computer.getProcessor(), processorTextField);
        processorTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(ramLabel);
        userPanel.add(ramTextField, "grow,push, wrap");
        updateField(computer.getRam(), ramTextField);
        ramTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "grow,push, wrap");
        updateField(computer.getServiceTag(), serviceTagTextField);

        userPanel.add(osLabel);
        userPanel.add(osTextField, "grow,push, wrap ");
        updateField(computer.getOs(), osTextField);

        userPanel.add(ageLabel);
        userPanel.add(ageTextField, "grow,push, wrap");
        updateField(computer.getAge(), ageTextField);

        userPanel.add(shiftingLabel);
        userPanel.add(shiftingTextChbox, "grow,push, wrap");
        updateBooleanField(computer.isShifting(), shiftingTextChbox);

        userPanel.add(purchasingDateLabel);
        userPanel.add(purchasingDateTextField, "grow,push, wrap");
        updateField(computer.getPurchaseDate(), purchasingDateTextField);

        userPanel.add(licensesLabel);
        userPanel.add(licensesTextField, "grow,push, wrap");
        updateField(computer.getLicenses(), licensesTextField);

        userPanel.add(userLabel);
        userPanel.add(userTextField, "grow,push, wrap");
        updateField(computer.getUserAccount(), userTextField);

        userPanel.add(screensLabel);
        userPanel.add(screensTextField, "grow,push, wrap");
        updateField(computer.getScreens(), screensTextField);

        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue? ", "Confirm",
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
        String computerName = computerNameTextField.getText();
        String osName = osTextField.getText();
        String ram = ramTextField.getText();
        String processor = processorTextField.getText();
        String serviceTage = serviceTagTextField.getText();
        String age = ageTextField.getText();

        //String user = userTextField.getText();
        //String screens = screensTextField.getText();

        Computer computer = new Computer();
        computer.setName(computerName);
        computer.setOs(osName);
        computer.setOs(ram);
        computer.setProcessor(processor);
        computer.setServiceTag(serviceTage);
        computer.setAge(Integer.parseInt(age));
        computer.setShifting(shiftingTextChbox.isSelected());
        computerSupplierValid = () -> {
            return computer;
        };
        this.dispose();
    }

    private void checkFields() {
        addButton.setEnabled(!(computerNameTextField.getText().isEmpty() || ramTextField.getText().isEmpty()
                || osTextField.getText().isEmpty() || processorTextField.getText().isEmpty()));
    }

    private void updateField(Object property, JTextComponent component) {
        if (property != null) {
            component.setText(String.valueOf(property));
        } else {
            component.setText("");
        }
    }

    private void updateBooleanField(Boolean property, JCheckBox component) {
        if (property != null) {
            component.setSelected(property);
        } else {
            component.setText("");
        }
    }
}
