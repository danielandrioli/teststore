package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductAddedToCartFrame;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.productListSizeOnHomepage;
import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class BasicStoreNavigationTest extends BaseWebTest {
    @BeforeMethod
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL);
    }

    @Test
    public void checkNumberOfProductsDisplayedOnHomepage() {
        StoreHomePage homePage = new StoreHomePage(driver);

        List<Product> products = homePage.getProducts();
        Assert.assertEquals(products.size(), productListSizeOnHomepage);
    }

    @Test(description = "Testing whether the product page opens.")
    public void clickOnProduct() {
        String productName = "Hummingbird Printed Sweater";
        Product product = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase(productName))
                .getProductFrame()
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(productName));
    }

    @Test(description = "Testing whether the ‘continue shopping button’ leaves the user on the home page and whether the cart number increases.")
    public void clickOnQuickViewAndAddToCartAndContinueShopping() {
        String productName = "Mug The Best Is Yet To Come";
        StoreHomePage homePage = new StoreHomePage(driver);
        ProductAddedToCartFrame productAddedFrame = homePage
                .clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .clickOnAddToCartBtn();

        Assert.assertTrue(productAddedFrame.getProduct().getName().equalsIgnoreCase(productName));

        productAddedFrame.clickContinueShopping();
        Assert.assertEquals(driver.getTitle(), StoreHomePage.pageTitle);
        Assert.assertEquals(homePage.getCartProductsCount(), 1);
    }
}
