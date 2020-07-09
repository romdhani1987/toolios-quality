package fr.romdhani.aymen.toolios.controller.user;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSession {
    private static final String CURRENT_USER = "currentUser";
    private List<AuthorizedAction> authorizedActionList = new ArrayList<>();
    private Optional<String> currentLogin = Optional.empty();
    private PropertyChangeSupport propertyChangeSupport;

    public List<AuthorizedAction> getAuthorizedActionList() {
        return authorizedActionList;
    }

    public void setAuthorizedActionList(List<AuthorizedAction> authorizedActionList) {
        this.authorizedActionList = authorizedActionList;
    }

    public Optional<String> getCurrentLogin() {
        return currentLogin;
    }

    public void setCurrentLogin(Optional<String> currentLogin) {
        this.currentLogin = currentLogin;
    }

    private UserSession() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    private static class UserSessionInstance {
        final static UserSession userSession = new UserSession();
    }

    private UserSession getInstance() {
        return UserSessionInstance.userSession;
    }
}
