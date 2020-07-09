package fr.romdhani.aymen.toolios.view.dialog.request;

import javax.swing.*;

/**
 * Creates  and displays  purchase resuest dialog
 *
 * @author aromdhani
 */
public class PurchaseRequestDialog extends JDialog {

    private JTextField owner;
    private JTextField supervisor;
    private JTextField dateOfPurchase;
    private JTextArea description;
    private JTextField provider;
    private JTextField details;
    private JTextField productDescription;
    private JTextField tva;
    private JTextField quantity;
    private JTextField PriceHt;
    private JTextField totalArticleHt;
    private JTextField totalArticleTva;
    private JTextField totalHT;
    private JTextField totalTva;
    private JTextField totalTtc;
    private JButton addNewArticle;
    private JCheckBox isValidationRequired;
    private JCheckBox notifySupervisor;
    private JButton validButton;
    private JButton CancelButton;
    private JButton resetButton;

    private void initComponents() {
        JLabel ownerLabel;
        JLabel supervisorLabel;
        JLabel dateOfPurchaseLabel;
        JLabel descriptionLabel;
        JLabel providerLabel;
        JLabel detailsLabel;
        JLabel productDescriptionLabel;
        JLabel tvaLabel;
        JLabel quantityLabel;
        JLabel PriceHtLabel;
        JLabel totalArticleHtLabel;
        JLabel totalArticleTvaLabel;
        JLabel totalTvaLabel;
        JButton addNewArticleLabel;
        JCheckBox isValidationRequiredLabel;
        JCheckBox notifySupervisorLabel;
    }
}
