package fr.romdhani.aymen.toolios.view.dialog.informatique;

import fr.romdhani.aymen.toolios.core.orm.Computer;
import fr.romdhani.aymen.toolios.core.orm.Screen;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

/**
 * Creates and displays scren diaolg
 */
public class ScreenDialog extends JDialog {

    private static final String ERROR = "Error occurred: maybe some fields are incorrect!";
    private JButton addButton = new JButton("Apply");
    private JButton clearButton = new JButton("Clear");
    private JButton cancelButton = new JButton("Cancel");
    private JTextField screenNameTextField = new JTextField();
    private JTextField serialNumberTextField = new JTextField();
    private JTextField serviceTagTextField = new JTextField();
    private JTextField ageField = new JTextField();
    private JComboBox<Computer> computerComboBox = new JComboBox();
    private UtilDateModel model = new UtilDateModel();
    private JLabel errorLabel;
    private Optional<Screen> screenOpt = Optional.empty();
    private boolean isEditable = false;
    private Supplier<Optional<Screen>> screenSupplierValid = () -> {
        return screenOpt;
    };

    public ScreenDialog() {
        super();
        initComponents();
    }

    public ScreenDialog(Screen screenOpt, boolean isEditable) {
        super();
        this.screenOpt = Optional.of(screenOpt);
        this.isEditable = isEditable;
        initComponents();
    }

    public Supplier<Optional<Screen>> getScreenSupplierValid() {
        return screenSupplierValid;
    }

    private void initComponents() {
        setSize(800, 600);
        setTitle("New Screen");
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new MigLayout("", "[:200:]10[:300:]"));
        JLabel computerNameLabel = new JLabel("Screen name/brand * ");
        JLabel serialNumberLabel = new JLabel("Serial number * ");
        JLabel serviceLabel = new JLabel("Service tag * ");
        JLabel ageLabel = new JLabel("Age ");
        JLabel purchasingDateLabel = new JLabel("Purchase date ");
        JLabel computerLabel = new JLabel("Computer ");
        // Layout
        if (screenOpt.isPresent() && isEditable) {
            screenNameTextField.setText(screenOpt.get().getName());
        }
        userPanel.add(computerNameLabel);
        userPanel.add(screenNameTextField, "growx,push, wrap");

        if (screenOpt.isPresent() && isEditable) {
            serialNumberTextField.setText(screenOpt.get().getSerialNumber());
        }
        userPanel.add(serialNumberLabel);
        userPanel.add(serialNumberTextField, "growx,push, wrap");

        if (screenOpt.isPresent() && isEditable) {
            serviceTagTextField.setText(screenOpt.get().getServiceTag());
        }
        userPanel.add(serviceLabel);
        userPanel.add(serviceTagTextField, "growx,push, wrap");

        userPanel.add(ageLabel);
        ageField.setEditable(false);
        userPanel.add(ageField, "growx,push, wrap");

        userPanel.add(purchasingDateLabel);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        userPanel.add(datePicker, "growx,push, wrap");

        userPanel.add(computerLabel);
        List<Computer> computers = (List<Computer>) getSession().createQuery("from Computer").list();
        computers.forEach(computer -> {
            computerComboBox.addItem(computer);
        });
        if (screenOpt.isPresent() && isEditable) {
            computerComboBox.setSelectedItem(screenOpt.get().getComputer());
        }
        userPanel.add(computerComboBox, "growx,push, wrap");

        clearButton.addActionListener(e -> {
            clear();
        });
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                screenOpt = Optional.empty();
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
        add(new JScrollPane(userPanel), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                screenOpt = Optional.empty();
            }
        });
    }

    private void applyChanges() {
        if (!StringUtils.isNullOrEmpty(screenNameTextField.getText())
                && !StringUtils.isNullOrEmpty(serialNumberTextField.getText())
                && !StringUtils.isNullOrEmpty(serviceTagTextField.getText())) {
            String computerName = screenNameTextField.getText();
            String serial = serialNumberTextField.getText();
            String serviceTag = serviceTagTextField.getText();

            if (isEditable && screenOpt.isPresent()) {
                screenOpt.get().setName(computerName);
                screenOpt.get().setSerialNumber(serial);
                screenOpt.get().setServiceTag(serviceTag);
                screenOpt.get().setComputer((Computer) computerComboBox.getSelectedItem());
            } else {
                Timestamp creationTimestamp = new Timestamp(model.getValue().getTime());
                int age = computeAge(creationTimestamp.toLocalDateTime().toLocalDate(), LocalDate.now());
                screenOpt = Optional.of(new Screen());
                screenOpt.get().setName(computerName);
                screenOpt.get().setSerialNumber(serial);
                screenOpt.get().setServiceTag(serviceTag);
                screenOpt.get().setAge(age);
                screenOpt.get().setPurchaseDate(creationTimestamp);
                screenOpt.get().setComputer((Computer) computerComboBox.getSelectedItem());
            }
            errorLabel.setVisible(false);
            screenSupplierValid = () -> {
                return screenOpt;
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
        componentList.add(screenNameTextField);
        componentList.add(serialNumberTextField);
        componentList.add(serviceTagTextField);
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
