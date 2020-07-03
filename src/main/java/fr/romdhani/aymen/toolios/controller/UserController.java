package fr.romdhani.aymen.toolios.controller;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.core.service.UserAccountService;
import fr.romdhani.aymen.toolios.view.panel.UsersPanel;

public class UserController {
    private UsersPanel usersPanel;
    private UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
        usersPanel = new UsersPanel(this);
        usersPanel.getUserModelObject().addAllUsers(userAccountService.findAll());
    }

    public UsersPanel getUsersPanel() {
        return usersPanel;
    }

    public void setUsersPanel(UsersPanel usersPanel) {
        this.usersPanel = usersPanel;
    }

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public void addUserToDb(UserAccount user) {
        userAccountService.persist(user);
    }
}
