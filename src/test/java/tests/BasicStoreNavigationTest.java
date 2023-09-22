package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.StoreProductPage;
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

    @Test(description = "Verify whether the click on the product opens its page and whether the 'add to cart' " +
            "button opens a new frame")
    public void clickOnProductAndAddToCart() {
        String productName = "Hummingbird Printed Sweater";
        Product product = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase(productName))
                .getProductFrame()
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(productName));

        ProductAddedToCartFrame pAddedFrame = new StoreProductPage(driver, productName)
                .getProductFrame().clickOnAddToCartBtn();
        Assert.assertTrue(productName.equalsIgnoreCase(pAddedFrame.getProduct().getName()));
        driver.manage().deleteAllCookies();
    }

    @Test(description = "Verify whether the 'quick view' button opens the product frame.")
    public void clickOnQuickView() {
        String productName = "Mug The Best Is Yet To Come";
        Product product = new StoreHomePage(driver)
                .clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
    }

    @Test(description = "Verify whether the 'continue shopping' button leaves the user on the page, " +
            "and whether the cart number increases after the product was added.",
            dependsOnMethods = {"clickOnQuickView", "clickOnProductAndAddToCart"})
    public void addProductToCartAndContinueShopping() {
        String productName = "Mug The Best Is Yet To Come";
        StoreHomePage homePage = new StoreHomePage(driver);
        homePage.clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .clickOnAddToCartBtn().clickContinueShopping();

        Assert.assertEquals(driver.getTitle(), StoreHomePage.pageTitle);
        Assert.assertEquals(homePage.getCartProductsCount(), 1);
    }
}
