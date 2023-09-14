package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.components.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.productListSizeOnHomepage;
import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class ProductTest extends BaseWebTest {
//TROCAR NOME DESSA CLASSE. O QUE ELA VAI TESTAR, TB?
    @Test(priority = -1)
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL);
    }

    @Test
    public void checkNumberOfProductsDisplayed() {
        StoreHomePage homePage = new StoreHomePage(driver);

        for (Product product: homePage.getProducts()) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }

        List<Product> products = homePage.getProducts();
        Assert.assertEquals(products.size(), productListSizeOnHomepage);
    }
}
