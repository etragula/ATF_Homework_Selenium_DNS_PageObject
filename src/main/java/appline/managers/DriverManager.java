package appline.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static appline.utils.PropertyConstants.PATH_TO_DRIVER;

public class DriverManager {

    private static PropertyManager propertyManager = PropertyManager.getPropertyManager();

    private static WebDriver driver;

    private DriverManager() {
    }

    private static void initManager() {
        System.setProperty("webdriver.chrome.driver", propertyManager.getProperty(PATH_TO_DRIVER));
        driver = new ChromeDriver();

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initManager();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
