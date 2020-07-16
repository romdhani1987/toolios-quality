package fr.romdhani.aymen.toolios.controller.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author aromdhani
 */

public class ConfigProperties {
    Boolean isPopulated = false;
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
            String propFileName = "toolios.properties";

            inputStream = new FileInputStream("src/main/resources/config/toolios.properties");

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            isPopulated = Boolean.valueOf(prop.getProperty("isPopulated"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return isPopulated;
    }
}
