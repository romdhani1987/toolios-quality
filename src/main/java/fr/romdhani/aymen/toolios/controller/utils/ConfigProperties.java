package fr.romdhani.aymen.toolios.controller.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Crunchify.com
 */

public class ConfigProperties {
    Boolean isDatabasePopulated = false;
    InputStream inputStream;

    private ConfigProperties() {

    }

    private static class ConfigPropertiesHolder {
        static final ConfigProperties instance = new ConfigProperties();
    }

    public static ConfigProperties getInstance() {
        return ConfigPropertiesHolder.instance;
    }

    public Boolean isDatabaseInitialized() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = new FileInputStream("src/main/resources/config/config.properties");

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            isDatabasePopulated = Boolean.valueOf(prop.getProperty("populate.database"));

            System.out.println("*** Start to populate the database: " + isDatabasePopulated);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return isDatabasePopulated;
    }
}
