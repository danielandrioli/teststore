package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductSmallContainer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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

    @Test(dependsOnMethods = "checkNumberOfProductsDisplayed",
            description = "Testing if the product page opens.")
    public void clickOnProduct() {
        String productName = "Hummingbird Printed Sweater";
        Product product = new StoreHomePage(driver)
                .clickOnProduct(p -> p.getName().equalsIgnoreCase(productName)) // return StoreHomePage
                .getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "clickOnProduct") //NA REAL NAO DEPENDE, SÓ TO CRIANDO UMA ORDEM
    public void clickOnQuickViewAndAddToCart() {
        String productName = "Mug The Best Is Yet To Come";
        Product product = new StoreHomePage(driver)
                .clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName))
                .addProductToCart()
                .getProduct();

        Assert.assertEquals(product.getName(), productName.toUpperCase());

//        productFrame.clickOnCartBtn(); //CONTINUAR AQUI... ASSERT Q A MSG RECEBIDA FOI CORRETA E O NÚMERO NO CARRINHO AUMENTOU.
    }

//    @Test(dependsOnMethods = "clickOnQuickView")
//    public void addProductToCart() {
//        StoreHomePage homePage = new StoreHomePage(driver);
//        homePage.addProductToCart();
//    }

    @AfterClass
    @Override
    public void teardown() {

    }
}
