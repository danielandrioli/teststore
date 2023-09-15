package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreHomePage;
import com.daniboy.pageobjects.store.StoreProductPage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductFrame;
import com.daniboy.pageobjects.store.components.ProductSmallContainer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

        for (ProductSmallContainer pContainer: homePage.getProductsContainers()) {
            System.out.println(pContainer.getProduct().getName() + " - " + pContainer.getProduct().getPrice());
        }

        List<ProductSmallContainer> products = homePage.getProductsContainers();
        Assert.assertEquals(products.size(), productListSizeOnHomepage);
    }

    @Test(dependsOnMethods = "checkNumberOfProductsDisplayed")
    public void clickOnProduct() { //Testing if the product page opens.
        StoreHomePage homePage = new StoreHomePage(driver);
        String productName = "Hummingbird Printed Sweater";
        Product product = homePage
                .clickOnProduct(p -> p.getName().equalsIgnoreCase(productName))
                .getProductFrame().getProduct();

        Assert.assertTrue(product.getName().equalsIgnoreCase(productName));
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "clickOnProduct") //NA REAL NAO DEPENDE, SÓ TO CRIANDO UMA ORDEM
    public void clickOnQuickViewAndAddToCart() {
        StoreHomePage homePage = new StoreHomePage(driver);
        String productName = "Mug The Best Is Yet To Come";
        ProductFrame productF = homePage
                .clickOnQuickView(p -> p.getName().equalsIgnoreCase(productName));

        Assert.assertEquals(productF.getProduct().getName(), productName.toUpperCase());

        productF.clickOnCartBtn(); //CONTINUAR AQUI... ASSERT Q A MSG RECEBIDA FOI CORRETA E O NÚMERO NO CARRINHO AUMENTOU.
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
