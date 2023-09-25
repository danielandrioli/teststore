package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.ProductsToTest;
import com.daniboy.pageobjects.store.StoreCartPage;
import com.daniboy.pageobjects.store.StoreProductPage;
import com.daniboy.pageobjects.store.components.Product;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.*;

public class ProductToCartTest extends BaseWebTest {
    private Product product;

    @BeforeClass
    public void enterUrl() {
        ProductsToTest productToTest = ProductsToTest.PRODUCT_3; // Today is a good day Framed poster | 60x90cm | 2
        driver.get(storeTestSiteBaseURL + productToTest.getPath());
        product = productToTest.getProduct();
    }

    //S3T1
    @Test(description = "Given I'm on the product's page, when I select variant and increase size and click on add to cart " +
            "and on proceed to checkout, then I should go to the cart page and it should contain the correct product information.")
    public void selectProductVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout() {
        StoreCartPage cartPage = new StoreProductPage(driver, product.getName())
                .getProductFrame()
                .selectVariant(product.getVariants().get(0)) // dimension 60x90cm variant
                .clickOnIncreaseQuantity()
                .clickOnAddToCartBtn()
                .clickProceedToCheckout();

        List<Product> cartList = cartPage.getProductList();

        Assert.assertEquals(driver.getTitle(), StoreCartPage.pageTitle);
        Assert.assertEquals(cartPage.getItensQuantity(), product.getQuantity());
        Assert.assertTrue(cartList.contains(product));
        Assert.assertEquals(cartPage.getTotalValue() - cartPage.getShippingCost(), product.getQuantity() * product.getPrice());

        for (Product prod : cartPage.getProductList()) {
            Reporter.log("Name: %s - Quantity: %s - Variant: %s - Price per unit: %s".formatted(
                    prod.getName(), prod.getQuantity(), prod.getVariants(), prod.getPrice()
            ));
        }
    }

    //S3T2
    @Test(description = "Given I'm on cart page, when I enter a valid promo code, then I should get a discount.",
            dependsOnMethods = "selectProductVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout")
    public void addPromoCode() {
        StoreCartPage cartPage = new StoreCartPage(driver);
        Double valueAfterCode = cartPage
                .clickOnHavePromoCode()
                .enterPromoCode(promoCode)
                .getTotalValue();

        Double expectedPrice = (product.getPrice() -
                (product.getPrice() * promoDiscount)) * product.getQuantity() + cartPage.getShippingCost();

        Assert.assertEquals(valueAfterCode, expectedPrice);
        Reporter.log("Price after promo code: " + valueAfterCode + " (shipping cost included).");
    }
}
