package appline.pages;

import appline.products.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import org.junit.Assert;

import static appline.managers.DriverManager.getDriver;

public class BucketPage extends BasePage {

    private Product deletedProduct;

    //          ---------- Удаление определенного товара в корзине ----------
    public BucketPage deleteThisProduct(String whatToDelete) {
        List<WebElement> bucket = getDriver().findElements(By.xpath("//div[@class='cart-items__product-info']"));
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).findElement(By.xpath(".//div[@class='cart-items__product-name']//a"))
                    .getText().toLowerCase().contains(whatToDelete.toLowerCase())) {
                sleepForInterval(500);
                clickElement(bucket.get(i).findElement(By.xpath(".//button[contains(text(), 'Удалить')]")));
                bucket.remove(bucket.get(i));
                listOfProductsInTheBucket.forEach(product -> {
                    if (product.getName().toLowerCase().contains(whatToDelete.toLowerCase()))
                        deletedProduct = product;
                });
                listOfProductsInTheBucket.removeIf(product -> product.getName().toLowerCase().contains(whatToDelete));
                showProductsInTheBucket();
                int sum = 0;
                for (Product product : listOfProductsInTheBucket) {
                    sum += product.getPrice() + product.getWarrantyPrice();
                }
                sleepForInterval(2000);
                Assert.assertEquals("Сумма корзины не совпадает с суммой цен добавленных товаров", sum,
                        fromStringToInteger(getDriver().findElement(By.xpath("//span[@class='cart-link__price']")).getText()));
                break;
            }
        }
        return this;
    }

    //          ---------- Изменить количество определенного продукта в корзине ----------
    public BucketPage addThisProduct(String whatProductToAdd, int howManyToAdd) {
        sleepForInterval(750);
        List<WebElement> bucket = getDriver().findElements(By.xpath("//div[@class='cart-items__product-info']"));
        for (WebElement product : bucket) {
            if (product.findElement(By.xpath(".//div[@class='cart-items__product-name']//a"))
                    .getText().toLowerCase().contains(whatProductToAdd.toLowerCase())) {
                product.findElement(By.xpath(".//input[@class=\"count-buttons__input\"]")).clear();
                clickElement(product.findElement(By.xpath(".//input[@class=\"count-buttons__input\"]")));
                product.findElement(By.xpath(".//input[@class=\"count-buttons__input\"]"))
                        .sendKeys(String.valueOf(howManyToAdd + 1));
                break;
            }
        }
        Product addThisProduct = new Product();
        for (Product product : listOfProductsInTheBucket) {
            if (product.getName().toLowerCase().contains(whatProductToAdd.toLowerCase())) {
                addThisProduct = product;
            }
        }
        for (; howManyToAdd > 0; howManyToAdd--) {
            listOfProductsInTheBucket.add(addThisProduct);
        }
        int sum = 0;
        for (Product product : listOfProductsInTheBucket) {
            sum += product.getPrice() + product.getWarrantyPrice();
        }
        sleepForInterval(3000);
        Assert.assertEquals("Сумма корзины не совпадает с суммой цен добавленных товаров", sum,
                fromStringToInteger(getDriver().findElement(By.xpath("//span[@class='cart-link__price']")).getText()));
        return this;
    }

    //          ---------- Возврат удаленных продуктов в корзину ----------
    public BucketPage returnDeletedProducts() {
        clickElement(getDriver().findElement(By.xpath("//span//span[@class=\"restore-last-removed\"]")));
        listOfProductsInTheBucket.add(deletedProduct);
        int sum = 0;
        for (Product product : listOfProductsInTheBucket) {
            sum += product.getPrice() + product.getWarrantyPrice();
        }
        sleepForInterval(2000);
        Assert.assertEquals("Сумма корзины не совпадает с суммой цен добавленных товаров", sum,
                fromStringToInteger(getDriver().findElement(By.xpath("//span[@class='cart-link__price']")).getText()));
        return this;
    }
}
