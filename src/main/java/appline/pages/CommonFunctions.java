package appline.pages;

import appline.managers.PageManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static appline.managers.DriverManager.getDriver;

public class CommonFunctions {

    //        ---------- Objects ----------
    protected PageManager app = PageManager.getManagerPages();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 60, 1000);

    //        ---------- Functions ----------
    protected int fromStringToInteger(String str) {
        int i = 0;
        int sign = 1;
        double result = 0;

        if (str == null || str.length() < 1) {
            return 0;
        }
        // trim white spaces
        str = str.trim();
        // check negative or positive
        if (str.charAt(0) == '-') {
            sign *= -1;
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9' || str.charAt(i) == 32) {
            if (str.charAt(i) != 32) {
                result = result * 10 + (str.charAt(i) - '0');
            }
            if (str.length() == i + 1)
                break;
            i++;
        }
        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) (sign * result);
    }

    protected void sleepForInterval(int interval) {
        try {
            Thread.sleep(interval);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        sleepForInterval(1000);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}