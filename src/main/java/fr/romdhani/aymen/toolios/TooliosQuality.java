package fr.romdhani.aymen.toolios;

import fr.romdhani.aymen.toolios.controller.utils.ConfigProperties;
import fr.romdhani.aymen.toolios.controller.utils.DatabaseUtils;
import fr.romdhani.aymen.toolios.view.TooliosQualityView;

import java.awt.*;
import java.io.IOException;

/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class TooliosQuality {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("*** Start Toolios-Quality ***");
        try {
            if (ConfigProperties.getInstance().isDatabaseInitialized()) {
                DatabaseUtils.getInstance().populateRoles();
                DatabaseUtils.getInstance().populateFunction();
                DatabaseUtils.getInstance().createCompany();
               //DatabaseUtils.getInstance().createGroups();
            }
            TooliosQualityView tooliosQualityView = new TooliosQualityView("Toolios-Quality");
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            tooliosQualityView.setPreferredSize(new Dimension(1024, 800));
            tooliosQualityView.pack();
            tooliosQualityView.setLocationRelativeTo(null);
            tooliosQualityView.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
