package fr.romdhani.aymen.toolios.view.buttons;

import javax.swing.*;
import java.awt.*;

public class TooliosButton extends JButton {
    private String label;
    private Dimension dimension;
    private Icon icon;
    private Action action;

    public TooliosButton(Icon icon, Action action) {
        super(icon);
        super.setAction(action);
        this.icon = icon;
        this.action = action;
    }

    public TooliosButton(String label) {
        super(label);
        this.label = label;
        initComponenets();
    }

    public TooliosButton() {
        super();
        initComponenets();
    }

    private void initComponenets() {
        this.setPreferredSize(new Dimension(100, 30));
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }
}
