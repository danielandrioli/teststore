package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreSearchPage;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductSortedBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class SearchFiltersTest extends BaseWebTest {

    @BeforeClass
    public void enterSearchPage() {
        driver.get(storeTestSiteBaseURL + "search?controller=search&s=mug");
    }

    //S4T1
    @Test(description = "Given I'm on search page, when I select 'Name, A to Z' sort filter, then I get the filtered results in that order.")
    public void verifySortByNameAtoZ() {
        List<Product> sortedProducts = new StoreSearchPage(driver)
                .sortBy(ProductSortedBy.NAME_A_TO_Z)
                .getProducts();

        for (int i = 0; sortedProducts.size() - 1 > i; i++) {
            Product product = sortedProducts.get(i);
            Product nextProduct = sortedProducts.get(i + 1);
            if (product.getName().compareToIgnoreCase(nextProduct.getName()) >= 0) {
                Assert.fail("The product list is not filtered by Name, A to Z.");
            }
        }
    }

    //S4T2
    @Test(description = "Given I'm on search page, when I select 'Name, Z to A' sort filter, then I get the filtered results in that order.")
    public void verifySortByNameZtoA() {
        List<Product> sortedProducts = new StoreSearchPage(driver)
                .sortBy(ProductSortedBy.NAME_Z_TO_A)
                .getProducts();

        for (int i = 0; sortedProducts.size() - 1 > i; i++) {
            Product product = sortedProducts.get(i);
            Product nextProduct = sortedProducts.get(i + 1);
            if (product.getName().compareToIgnoreCase(nextProduct.getName()) <= 0) {
                Assert.fail("The product list is not filtered by Name, Z to A.");
            }
        }
    }

    //S4T3
    @Test(description = "Given I'm on search page, when I select 'Price, low to high' sort filter, then I get the filtered results in that order.")
    public void verifySortByPriceLowToHigh() {
        List<Product> sortedProducts = new StoreSearchPage(driver)
                .sortBy(ProductSortedBy.PRICE_L_TO_H)
                .getProducts();

        for (int i = 0; sortedProducts.size() - 1 > i; i++) {
            Product product = sortedProducts.get(i);
            Product nextProduct = sortedProducts.get(i + 1);
            if (product.getPrice() > nextProduct.getPrice()) {
                Assert.fail("The product list is not filtered by Price, low to high.");
            }
        }
    }

    //S4T4
    @Test(description = "Given I'm on search page, when I select 'Price, high to low' sort filter, then I get the filtered results in that order.")
    public void verifySortByPriceHighToLow() {
        List<Product> sortedProducts = new StoreSearchPage(driver)
                .sortBy(ProductSortedBy.PRICE_H_TO_L)
                .getProducts();

        for (int i = 0; sortedProducts.size() - 1 > i; i++) {
            Product product = sortedProducts.get(i);
            System.out.println(product.getName());
            Product nextProduct = sortedProducts.get(i + 1);
            if (product.getPrice() < nextProduct.getPrice()) {
                Assert.fail("The product list is not filtered by Price, high to low.");
            }
        }
    }
}
