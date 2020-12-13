package appline.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import appline.products.Product;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.ArrayList;

import static appline.managers.DriverManager.getDriver;

public class BasePage extends CommonFunctions {
    //          ---------- Web-elements ----------
    @FindBy(xpath = "//nav//input")
    protected WebElement searchBlock;

    @FindBy(xpath = "//div[@id = 'product-page']")
    protected WebElement productPageChecker;

    @FindBy(xpath = "//div[@data-cart-product-id]")
    protected List<WebElement> productsInTheBucket;

    @FindBy(xpath = "//span[@class='cart-link__lbl']")
    protected WebElement bucketButton;

    @FindBy(xpath = "//button[contains(text(), 'Купить')]")
    protected WebElement buyButton;

    @FindBy(xpath = "")
    protected WebElement productTitle;

    @FindBy(xpath = "//div[@class='product-info__title-link']//a")
    protected List<WebElement> foundListOfProducts;

    @FindBy(xpath = "//nav//span[contains(@class, 'icon_search')]")
    protected WebElement searchButton;

    //          ---------- ListOfProducts ----------
    protected static List<Product> listOfAllAddedProducts = new ArrayList<>();
    protected static List<Product> listOfProductsInTheBucket = new ArrayList<>();

    public static void showAllAddedProducts() {
        listOfAllAddedProducts.forEach(product -> System.out.println(product.getName() +
                "\nPrice = " + product.getPrice() +
                "\nwarrantyPrice = " + product.getWarrantyPrice() +
                "\nyearsOfWarranty = " + product.getYearsOfWarranty()));
    }

    public static void showProductsInTheBucket() {
        listOfProductsInTheBucket.forEach(product -> System.out.println(product.getName() +
                "\nPrice = " + product.getPrice() +
                "\nwarrantyPrice = " + product.getWarrantyPrice() +
                "\nyearsOfWarranty = " + product.getYearsOfWarranty()));
    }

    //          ---------- Поиск продуктов ----------
    protected void searchFor(String whatToSearch) {
        if (whatToSearch.equals("")) {
            Assert.fail("Введите название или категорию товара для поиска.");
        }
        clickElement(searchBlock);
        searchBlock.clear();
        wait.until(ExpectedConditions.visibilityOf(searchBlock));
        searchBlock.sendKeys(whatToSearch);
        clickElement(searchButton);
    }

    //          ---------- Добавление продукта в корзину ----------
    protected void addToBucket() {
        sleepForInterval(500);
        clickElement(buyButton);
    }

    //          ---------- Переход в корзину ----------
    protected void goToBucket() {
        clickElement(bucketButton);
        rememberInfoAboutProductsInTheBucket();
        sleepForInterval(750);
        checkTheSumOfBucket();
    }

    //          ---------- Парсер информации в корзине ----------
    protected void rememberInfoAboutProductsInTheBucket() {
        if (productsInTheBucket.size() == 0) {
            Assert.fail("Корзина пуста.");
        }
        int i = 0;
        for (WebElement product : productsInTheBucket) {
            Product pr = new Product();
            pr.setName((product.findElement(By.xpath(".//div[@class=\"cart-items__product-name\"]//a")).getText()));
            pr.setPrice(fromStringToInteger(product.findElement(By.xpath(".//span[contains(@class, 'price__current')]")).getText()));
            if (listOfAllAddedProducts.get(i).getYearsOfWarranty() != 0) {
                try {
                    pr.setYearsOfWarranty(fromStringToInteger(product.findElement(By.xpath(".//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]")).getText()) / 12);
                    pr.setWarrantyPrice(fromStringToInteger(product.findElement(By.xpath(".//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]/following::span[@class=\"additional-warranties-row__price\"]")).getText()));
                } catch (NoSuchElementException e) {

                }
            }
            i++;
            listOfProductsInTheBucket.add(pr);
        }
    }

    //          ---------- Проверка цен продуктов их суммы, срока гарантии ----------
    protected void checkTheSumOfBucket() {
        int sumOfAddedProducts = 0;
        for (Product product : listOfAllAddedProducts) {
            sumOfAddedProducts += product.getPrice();
        }
        Assert.assertEquals("Сумма корзины не совпадает с суммой цен добавленных товаров", sumOfAddedProducts,
                fromStringToInteger(getDriver().findElement(By.xpath("//span[@class='cart-link__price']")).getText()));
        Assert.assertEquals("Количество купленных товаров не совпадает с их количеством в корзине",
                listOfAllAddedProducts.size(), listOfProductsInTheBucket.size());
        for (int i = 0; i < listOfAllAddedProducts.size(); i++) {
            Assert.assertEquals("Цена продукта \"" + listOfAllAddedProducts.get(i).getName() +
                            "\" не совпадает его ценой в корзине.", listOfAllAddedProducts.get(i).getPrice(),
                    listOfProductsInTheBucket.get(i).getPrice() + listOfProductsInTheBucket.get(i).getWarrantyPrice());
            Assert.assertEquals("Срок гарантии продукта \"" + listOfAllAddedProducts.get(i).getName() +
                            "\" не совпадает со сроком в корзине.", listOfAllAddedProducts.get(i).getYearsOfWarranty(),
                    listOfProductsInTheBucket.get(i).getYearsOfWarranty());
        }
    }

    //          ---------- PageFactory ----------
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }
}
