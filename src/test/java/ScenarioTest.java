import org.junit.Test;

public class ScenarioTest extends BaseTest {

    @Test
    public void test() {
        app.getStartPage()
                .searchingFor("playstation")
                .findProductFromTheList("slim")
                .chooseExtraWarrantyForProductAndAddToTheBucket(2)
                .searchingFor("tv lg")
                .findProductFromTheList("49")
                .chooseExtraWarrantyForProductAndAddToTheBucket(2)
                .searchingFor("detroit")
                .findProductFromTheList("Detroit")
                .addToTheBucket()
//                .searchingFor("wifi")
//                .findProductFromTheList("wifi")
//                .addToTheBucket()
//                .searchingFor("coffee")
//                .findProductFromTheList("coffee")
//                .addToTheBucket()
                .goToTheBucket()
                .deleteThisProduct("detroit")
                .addThisProduct("PlayStation", 2)
                .returnDeletedProducts();
//        showAddedProducts();
//        System.out.println();
//        showProductsInTheBucket();
    }
}
