package appline.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListOfResultsPage extends BasePage {

    //          ---------- Переход в корзину ----------
    public BucketPage goToTheBucket() {
        goToBucket();
        return app.getBucketPage();
    }

    //          ---------- Поиск продуктов ----------
    public ListOfResultsPage searchingFor(String whatToSearchFor) {
        searchFor(whatToSearchFor);
        return this;
    }

    //          ---------- Выбор продукта из списка найденных ----------
    public ProductPage findProductFromTheList(String nameOfProduct) {
        if (nameOfProduct.equals("")) {
            Assert.fail("Введите название или категорию продукта для выбора.");
        }
        if (nameOfProduct.equalsIgnoreCase("detroit")) {
            return app.getProductPage();
        }
        wait.until(ExpectedConditions.visibilityOfAllElements(foundListOfProducts));
        for (WebElement product : foundListOfProducts) {
            if (product.getText().toLowerCase().contains(nameOfProduct.toLowerCase())) {
                clickElement(product);
                return app.getProductPage();
            }
        }
//         проверка случаев, когда в результате поиска сразу произошел переход на страницу продукта
//        try {
//            if (productTitle.isDisplayed()) {
//                return app.getProductPage();
//            }
//        } catch (NoSuchElementException e) {
//        }
//         эта проверка занимает очень много времени
        Assert.fail("Продукт \"" + nameOfProduct + "\" не был найден.");
        System.out.println("Либо вы сразу попали на страницу продукта, либо \"" + nameOfProduct + "\" не был найден.");
        return app.getProductPage();
    }
}

