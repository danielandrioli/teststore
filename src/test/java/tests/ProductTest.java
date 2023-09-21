package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.ProductsToTest;
import com.daniboy.pageobjects.store.StoreCartPage;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.components.Product;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.*;

public class ProductTest extends BaseWebTest {
//TROCAR NOME DESSA CLASSE. O QUE ELA VAI TESTAR, TB?
//    private static Logger log = LogManager.getLogger(ProductTest.class);

    @Test(priority = -1)
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL);
    }

    @Test()
    public void enterProductPageThenSelectVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout() {
        Product product = ProductsToTest.PRODUCT_3.getProduct();
        // Today is a good day Framed poster -- dimension 60x90cm -- 2 units.

        StoreCartPage cartPage = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase("Today is a good day Framed...")) // Nome mto grande na homepage
                .getProductFrame()
                .selectVariant(product.getVariants().get(0)) // dimension 60x90cm variant
                .clickOnIncreaseQuantity()
                .clickOnAddToCartBtn()
                .clickProceedToCheckout();

        List<Product> cartList = cartPage.getProductList();

        Assert.assertEquals(driver.getTitle(), StoreCartPage.pageTitle);
        Assert.assertEquals(cartPage.getItensQuantity(), product.getQuantity());
        Assert.assertTrue(cartList.contains(product));
        Assert.assertEquals(product.getQuantity() * product.getPrice(), cartPage.getTotalValue() - cartPage.getShippingCost());

        for (Product prod : cartPage.getProductList()) {
            Reporter.log("Nome: %s - Quantidade: %s - Variante: %s - Preço unitário: %s".formatted(
                    prod.getName(), prod.getQuantity(), prod.getVariants(), prod.getPrice()
            ));
        }
    }


    @Test(dependsOnMethods = "enterProductPageThenSelectVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout")
    public void addPromoCode() {
        StoreCartPage cartPage = new StoreCartPage(driver);
        Double valueAfterCode = cartPage
                .clickOnHavePromoCode()
                .enterPromoCode(promoCode)
                .getTotalValue();

        Product p2t = ProductsToTest.PRODUCT_3.getProduct();
        Double expectedPrice = (p2t.getPrice() -
                (p2t.getPrice() * promoDiscount)) * p2t.getQuantity() + cartPage.getShippingCost();

        Assert.assertEquals(valueAfterCode, expectedPrice);
        Reporter.log("Valor após promo code: " + valueAfterCode + " (custo de envio incluso).");
    }

            //enterCartThenDecreaseQuantityForOneItemAndDeleteAnother //talvez colocar em outro teste. TesteDoCarrinho
    @AfterClass
    @Override
    public void teardown() {

    }
}
