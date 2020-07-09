package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class NewComputerDialog extends JDialog {
    private Supplier<Computer> computerSupplierValid = () -> {
        return null;
    };
    private Supplier<Computer> computerSupplierCancel;
    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");
    JTextField computerNameTextField = new JTextField();
    JTextField serialNumberTextField = new JTextField();
    JTextField processorTextField = new JTextField();
    JTextField ramTextField = new JTextField();
    JTextField serviceTagTextField = new JTextField();
    JTextField osTextField = new JTextField();
    JTextField ageTextField = new JTextField();
    JCheckBox shiftingTextField = new JCheckBox();
    JTextField purchasingDateTextField = new JTextField();
    JTextField userTextField = new JTextField();
    JTextField screensTextField = new JTextField();
    JTextField licensesTextField = new JTextField();

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
        computerNameTextField.addActionListener(e -> {
            checkFields();
        });

        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "grow,push, wrap");
        serialNumberTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(processorLabel);
        userPanel.add(processorTextField, "grow,push, wrap");
        processorTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(ramLabel);
        userPanel.add(ramTextField, "grow,push, wrap");
        ramTextField.addActionListener(e -> {
            checkFields();
        });
        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "grow,push, wrap");

        userPanel.add(osLabel);
        userPanel.add(osTextField, "grow,push, wrap ");

        userPanel.add(ageLabel);
        userPanel.add(ageTextField, "grow,push, wrap");

        userPanel.add(shiftingLabel);
        userPanel.add(shiftingTextField, "grow,push, wrap");

        userPanel.add(purchasingDateLabel);
        userPanel.add(purchasingDateTextField, "grow,push, wrap");

        userPanel.add(licensesLabel);
        userPanel.add(licensesTextField, "grow,push, wrap");

        userPanel.add(userLabel);
        userPanel.add(userTextField, "grow,push, wrap");

        userPanel.add(screensLabel);
        userPanel.add(screensTextField, "grow,push, wrap");

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
        computer.setShifting(shiftingTextField.isSelected());
        //   Timestamp parchasingDate = Timestamp.valueOf(purchasingDateTextField.getText());
        //  computer.setPurchaseDate(parchasingDate);
        computerSupplierValid = () -> {
            return computer;
        };
        this.dispose();
    }

    private void checkFields() {
        addButton.setEnabled(!(computerNameTextField.getText().isEmpty() || ramTextField.getText().isEmpty()
                || osTextField.getText().isEmpty() || processorTextField.getText().isEmpty()));
    }

    private void cancelActionPerofrmed() {
        this.dispose();
    }
}
