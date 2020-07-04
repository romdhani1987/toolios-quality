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
        refresh();
    }
    public UsersPanel getUsersPanel() {
        return usersPanel;
    }

    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public boolean addUserToDb(UserAccount user) {
        return userAccountService.persist(user);
    }

    public void deleteUserFromDb(UserAccount user) {
        userAccountService.delete(user.getId());
    }
    public void refresh() {
        usersPanel.getUserModelObject().addAllUsers(userAccountService.findAll());
    }
    public void deleteAllUserFromDb() {
        userAccountService.deleteAll();
    }
}
