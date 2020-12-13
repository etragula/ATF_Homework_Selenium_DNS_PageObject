package appline.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private final Properties properties = new Properties();

    private static PropertyManager PROPS = null;

    private PropertyManager() {
        try {
            properties.load(new FileInputStream(
                            new File("src/main/resources/" +
                            System.getProperty("env", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyManager getPropertyManager() {
        if (PROPS == null) {
            PROPS = new PropertyManager();
        }
        return PROPS;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defualt) {
        return properties.getProperty(key, defualt);
    }


}
