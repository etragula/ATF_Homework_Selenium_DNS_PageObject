import appline.managers.InitManager;
import appline.managers.PageManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    PageManager app = PageManager.getManagerPages();

    @BeforeClass
    public static void doBeforeClass() {
        InitManager.initFramework();
    }

//    @Before
//    public void beforeEach() {
//        getDriver().get(PropertyManager.getPropertyManager().getProperty(APP_URL));
//    }

    @AfterClass
    public static void doAfterClass() {
//        InitManager.quitFramework();
    }
}
