package appline.pages;

import appline.products.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static appline.managers.DriverManager.getDriver;

public class ProductPage extends BasePage {

    //          ---------- Переход в корзину ----------
    public BucketPage goToTheBucket() {
        goToBucket();
        return app.getBucketPage();
    }

    //          ---------- Поиск продуктов ----------
    public ListOfResultsPage searchingFor(String whatToSearchFor) {
        searchFor(whatToSearchFor);
        return app.getListOfResultsPage();
    }

    //          ---------- Добавление продукта в корзину ----------
    public ProductPage addToTheBucket() {
        Product pr = new Product();
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//span[contains(@class, 'price__current')]"))));
        pr.setName((getDriver().findElement(By.xpath(".//h1[@class=\"page-title price-item-title\"]")).getText()));
        pr.setPrice(fromStringToInteger(getDriver().findElement(By.xpath("//span[contains(@class, 'price__current')]")).getText()));
        listOfAllAddedProducts.add(pr);
        addToBucket();
        return this;
    }

    //          ---------- Выбор срока гарантии продукта и добавление в корзину ----------
    public ProductPage chooseExtraWarrantyForProductAndAddToTheBucket(int howManyYearsOfWarranty) {
        if (!(getDriver().findElements(By.xpath("//div[contains(@class, 'warranty')]")).size() > 0)) {
            Assert.fail("Услуга \"Дополнительной гарантии\" не доступна для данного продукта.");
        }
        Select select = new Select(getDriver().findElement(By.xpath("//select[@class='form-control']")));
        List<WebElement> elements = select.getOptions();
        switch (howManyYearsOfWarranty) {
            case 0:
                select.selectByIndex(0);
                break;
            case 1:
                if (elements.size() <= 1) {
                    Assert.fail("Дополнительная гарантия на период 1 года недоступна.");
                }
                select.selectByIndex(1);
                break;
            case 2:
                if (elements.size() <= 2) {
                    Assert.fail("Дополнительная гарантия на период 2 лет недоступна.");
                }
                select.selectByIndex(2);
                break;
            default:
                Assert.fail("Дополнительная гарантия на период " + howManyYearsOfWarranty + " (года/лет) недоступна.");
        }
        Product pr = new Product();
        pr.setName((getDriver().findElement(By.xpath(".//h1[@class=\"page-title price-item-title\"]")).getText()));
        pr.setPrice(fromStringToInteger(getDriver().findElement(By.xpath("//span[contains(@class, 'price__current')]")).getText()));
        Select select_1 = new Select(getDriver().findElement(By.xpath("//select[@class='form-control']")));
        pr.setYearsOfWarranty(fromStringToInteger(select_1.getFirstSelectedOption().getText()));
        listOfAllAddedProducts.add(pr);
        addToBucket();
        return this;
    }
}

