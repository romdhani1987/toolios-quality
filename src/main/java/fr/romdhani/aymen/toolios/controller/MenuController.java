package fr.romdhani.aymen.toolios.controller;

import fr.romdhani.aymen.toolios.view.menu.TooliosMenubar;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

public class MenuController {
    private TooliosMenubar tooliosMenubar;
    public MenuController() {
        tooliosMenubar = new TooliosMenubar(this);
    }

    public Boolean close() {
        Boolean isClosed = false;
        try {
            if (getSession() != null && getSession().isOpen()) {
                getSession().close();
                System.exit(0);
                isClosed = true;
            }
        } catch (Exception e) {
            try {
                getSession().getTransaction().rollback();
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.err.println(e);
        }
        return isClosed;
    }

    public TooliosMenubar getTooliosMenubar() {
        return tooliosMenubar;
    }

    public void setTooliosMenubar(TooliosMenubar tooliosMenubar) {
        this.tooliosMenubar = tooliosMenubar;
    }
}
