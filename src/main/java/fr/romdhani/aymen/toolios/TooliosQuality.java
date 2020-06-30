package fr.romdhani.aymen.toolios;

import fr.romdhani.aymen.toolios.core.orm.UserAccount;
import fr.romdhani.aymen.toolios.core.service.UserAccountService;
import fr.romdhani.aymen.toolios.view.TooliosQualityView;

import java.awt.*;
import java.util.List;

/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class TooliosQuality {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
      /*
        UserAccountService userAccountService = new UserAccountService();
        UserAccount book2 = new UserAccount("aymenromd", "romdhani");
        UserAccount book3 = new UserAccount("sarah", "romdhani");
        System.out.println("*** Persist - start ***");
        userAccountService.persist(book2);
        userAccountService.persist(book3);
        List<UserAccount> userAccountList = userAccountService.findAll();
        System.out.println("User Persisted are :");
        for (UserAccount userAccount : userAccountList) {
            System.out.println("-" + userAccount.getLogin());
        }
         System.out.println("*** Persist - end ***");
        */
        System.out.println("*** Start Toolios-Quality ***");
        TooliosQualityView tooliosQualityView = new TooliosQualityView("Toolios-Quality");
        tooliosQualityView.setPreferredSize(new Dimension(800, 600));
        tooliosQualityView.pack();
        tooliosQualityView.setVisible(true);
    }

}
