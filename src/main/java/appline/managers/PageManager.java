package appline.managers;

import appline.pages.StartPage;
import appline.pages.BucketPage;
import appline.pages.ProductPage;
import appline.pages.ListOfResultsPage;

public class PageManager {

    private static PageManager manager;

    private PageManager() {
    }

    StartPage startPage;
    BucketPage bucketPage;
    ProductPage productPage;
    ListOfResultsPage listOfResultsPage;

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public BucketPage getBucketPage() {
        if (bucketPage == null) {
            bucketPage = new BucketPage();
        }
        return bucketPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public static PageManager getManagerPages() {
        if (manager == null) {
            manager = new PageManager();
        }
        return manager;
    }

    public ListOfResultsPage getListOfResultsPage() {
        if (listOfResultsPage == null) {
            listOfResultsPage = new ListOfResultsPage();
        }
        return listOfResultsPage;
    }
}
