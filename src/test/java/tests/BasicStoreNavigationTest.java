package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.ProductsToTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.StoreProductPage;
import com.daniboy.pageobjects.store.StoreSearchPage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductAddedToCartFrame;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.*;

public class BasicStoreNavigationTest extends BaseWebTest {
    @BeforeMethod
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL);
    }

    @Test(description = "Given I'm on homepage, then the displayed product list should have 8 items.")
    public void checkNumberOfProductsDisplayedOnHomepage() {
        StoreHomePage homePage = new StoreHomePage(driver);

        List<Product> products = homePage.getProducts();
        Assert.assertEquals(products.size(), productListSizeOnHomepage);
    }
    //S2T1
    @Test(description = "Given I'm on homepage, when I click on a product, then I should go to the product's page.")
    public void clickOnProductOnTheHomepageList() {
        String productName = "Today is a good day Framed poster"; // Name is too long. In the homepage, it contains "..." after word 'framed'
        Product product = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase("Today is a good day Framed..."))
                .getProductFrame()
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(productName));
    }
    //S2T2
    @Test(description = "Given I'm on a product's page, when I click on add to cart, then the 'product added' frame should be shown.")
    public void clickOnAddToCartFromProductPage() {
        ProductsToTest mugTheBest = ProductsToTest.PRODUCT_1; // Mug The Best Is Yet To Come
        driver.get(storeTestSiteBaseURL + mugTheBest.getPath());
        String productName = mugTheBest.getProduct().getName();
        ProductAddedToCartFrame pAddedFrame = new StoreProductPage(driver, productName)
                .getProductFrame()
                .clickOnAddToCartBtn();

        Assert.assertTrue(pAddedFrame.getProduct().getName().equalsIgnoreCase(productName));
        Assert.assertTrue(pAddedFrame.getFrameMessage().contains(productAddedMessage)); // contains because the original message also contains an icon.
        driver.manage().deleteAllCookies();
    }
    //S2T3
    @Test(description = "Given I'm on homepage, when I click on a product's quick view, then the product's frame should be shown.")
    public void clickOnQuickView() {
        String productName = "Mug The Best Is Yet To Come";
        Product product = new StoreHomePage(driver)
                .clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
    }
    //S2T4
    @Test(description = "Given I'm on homepage, when I click on a product's Quick View, and click on add to cart button, " +
            "and click on continue shopping button, then I'm back on homepage and the cart icon shows 1 item.",
            dependsOnMethods = {"clickOnQuickView", "clickOnAddToCartFromProductPage"})
    public void addProductToCartAndContinueShopping() {
        String productName = "Mug The Best Is Yet To Come";
        StoreHomePage homePage = new StoreHomePage(driver);
        homePage.clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .clickOnAddToCartBtn()
                .clickContinueShopping();

        Assert.assertEquals(driver.getTitle(), StoreHomePage.pageTitle);
        Assert.assertEquals(homePage.getCartProductsCount(), 1);
        driver.manage().deleteAllCookies();
    }
    //S2T5
    @Test(description = "Given I'm on homepage, when I type on the search bar and enter, then I should go to the search page.")
    public void searchForAnItemUsingSearchBar() {
        new StoreHomePage(driver).searchFor("Mug");
        Assert.assertEquals(driver.getTitle(), StoreSearchPage.pageTitle);
    }
}
