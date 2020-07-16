package fr.romdhani.aymen.toolios.controller.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read and write toolios configuration properties
 *
 * @author aromdhani
 */

public class ConfigProperties {
    private Boolean isPopulated = false;
    private InputStream inputStream;
    private static final String propFileName = "toolios.properties";

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

            inputStream = new FileInputStream("src/main/resources/config/toolios.properties");
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
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
