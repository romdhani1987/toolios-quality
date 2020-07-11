package fr.romdhani.aymen.toolios.view;

import fr.romdhani.aymen.toolios.controller.MenuController;
import fr.romdhani.aymen.toolios.view.menu.TooliosMenubar;
import fr.romdhani.aymen.toolios.view.panel.EasTPanel;
import fr.romdhani.aymen.toolios.view.panel.FooterPanel;
import fr.romdhani.aymen.toolios.view.panel.MainPanel;
import fr.romdhani.aymen.toolios.view.panel.WestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TooliosQualityView extends JFrame {

    private TooliosMenubar menuToolbar;
    private MainPanel mainPanel;
    private FooterPanel footerPanel;
    private EasTPanel easTPanel;
    private WestPanel westPanel;

    public TooliosQualityView() throws HeadlessException {
        super();
    }

    public TooliosQualityView(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    public TooliosQualityView(String title) throws HeadlessException {
        super(title);
        initComponents();
    }

    private void initComponents() {
        MenuController menuController = new MenuController();
        menuToolbar = menuController.getTooliosMenubar();
        mainPanel = new MainPanel();
        footerPanel = new FooterPanel();
        easTPanel = new EasTPanel();
        westPanel = new WestPanel();
        setJMenuBar(menuToolbar);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(easTPanel, BorderLayout.EAST);
        getContentPane().add(westPanel, BorderLayout.WEST);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int result = JOptionPane.showConfirmDialog(null, "Do you really want to exit Toolios-quality?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public FooterPanel getFooterPanel() {
        return footerPanel;
    }

    public void setFooterPanel(FooterPanel footerPanel) {
        this.footerPanel = footerPanel;
    }

    public EasTPanel getEasTPanel() {
        return easTPanel;
    }

    public void setEasTPanel(EasTPanel easTPanel) {
        this.easTPanel = easTPanel;
    }

    public WestPanel getWestPanel() {
        return westPanel;
    }

    public void setWestPanel(WestPanel westPanel) {
        this.westPanel = westPanel;
    }
}
