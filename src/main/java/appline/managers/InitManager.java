package appline.managers;

import java.util.concurrent.TimeUnit;

import static appline.managers.DriverManager.getDriver;
import static appline.utils.PropertyConstants.*;

public class InitManager {

    private static PropertyManager properties = PropertyManager.getPropertyManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty(IMPLICITY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().get(properties.getProperty(APP_URL));
    }

    public static void quitFramework() {
        getDriver().quit();
    }
}
