package appline.pages;

public class StartPage extends BasePage {

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
}
