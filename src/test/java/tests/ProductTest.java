package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.ProductsToTest;
import com.daniboy.pageobjects.store.StoreCartPage;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductAddedToCartFrame;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.daniboy.util.Constants.productListSizeOnHomepage;
import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class ProductTest extends BaseWebTest {
//TROCAR NOME DESSA CLASSE. O QUE ELA VAI TESTAR, TB?
    @Test(priority = -1)
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL);
    }



    @Test()
    public void enterProductPageThenSelectVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout() {
        Product product = ProductsToTest.PRODUCT_2.getProduct();
        // Hummingbird Printed Sweater -- size L -- 2 units.

        StoreCartPage cartPage = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase(product.getName()))
                .getProductFrame()
                .selectVariant(product.getVariants().get(0)) // size L variant
                .clickOnIncreaseQuantity()
                .clickOnAddToCartBtn()
                .clickProceedToCheckout();

        Assert.assertEquals(driver.getTitle(), StoreCartPage.pageTitle);
        Assert.assertEquals(cartPage.getItensQuantity(), product.getQuantity());

        //todo colocar num log esse for abaixo.
        for (Product prod : cartPage.getProductList()) {
            System.out.println("Nome: %s - Quantidade: %s - Variante: %s - Preço unitário: %s".formatted(
                    prod.getName(), prod.getQuantity(), prod.getVariants(), prod.getPrice()
            ));
        }

        List<Product> cartList = cartPage.getProductList();
        Assert.assertTrue(cartList.contains(product));

        //verificar o valor total!
    }


//    @Test(dependsOnMethods = "enterProductPageThenSelectVariantThenIncreaseQuantityThenAddToCartThenProceedToCheckout")
//    public void addProductTo

            //enterCartThenDecreaseQuantityForOneItemAndDeleteAnother //talvez colocar em outro teste. TesteDoCarrinho
            //enterCartIncludePromoCodeAndProceedToCheckout
    @AfterClass
    @Override
    public void teardown() {

    }
}
