package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductFrame;
import com.daniboy.pageobjects.store.components.ProductSmallContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Predicate;

public class StoreHomePage extends StoreBasePage {
    public static final String pageTitle = "teststore";
    @FindBy(css = ".product-miniature")
    private List<WebElement> productsWE;

    public StoreHomePage(WebDriver driver) {
        super(driver, pageTitle);
    }

    private List<ProductSmallContainer> getProductsContainers() {
        return productsWE.stream().map(webElement -> new ProductSmallContainer(webElement)).toList();
    }

    private ProductSmallContainer getProductContainer(Predicate<Product> condition) {
        return getProductsContainers().stream().filter(pContainer -> condition.test(pContainer.getProduct()))
                .findFirst().orElseThrow();
    }

    public List<Product> getProducts() {
        return getProductsContainers().stream().map(p -> p.getProduct()).toList();
    }

    public StoreProductPage clickOnProduct(Predicate<Product> condition) {
        ProductSmallContainer pContainer = getProductContainer(condition);
        pContainer.clickOnProduct();
        String name = pContainer.getProduct().getName().replace("...", ""); //Alguns produtos tem nome grande, então aparecem com "..." na página.
        return new StoreProductPage(driver, name);
    }

    public ProductFrame clickOnQuickView(Predicate<Product> condition) {
        getProductContainer(condition).clickOnQuickView(new Actions(driver));
        return new ProductFrame(driver);
    }
}
