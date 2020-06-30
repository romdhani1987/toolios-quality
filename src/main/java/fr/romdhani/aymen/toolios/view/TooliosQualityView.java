package fr.romdhani.aymen.toolios.view;

import fr.romdhani.aymen.toolios.view.menu.TooliosMenubar;
import fr.romdhani.aymen.toolios.view.panel.*;

import javax.swing.*;
import java.awt.*;

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
        menuToolbar = new TooliosMenubar();
        mainPanel = new MainPanel();
        footerPanel = new FooterPanel();
        easTPanel = new EasTPanel();
        westPanel = new WestPanel();
        setJMenuBar(menuToolbar);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(easTPanel, BorderLayout.EAST);
        getContentPane().add(westPanel, BorderLayout.WEST);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
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
