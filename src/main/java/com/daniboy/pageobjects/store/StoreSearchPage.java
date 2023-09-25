package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductSmallContainer;
import com.daniboy.pageobjects.store.components.ProductSortedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StoreSearchPage extends StoreBasePage {
    public static final String pageTitle = "Search";
    @FindBy(css = ".dropdown-menu")
    private WebElement dropdownMenu;
    @FindBy(css = ".sort-by-row button")
    private WebElement sortBtn;
    @FindBy(css = ".product-miniature")
    private List<WebElement> productsWE;

    public StoreSearchPage(WebDriver driver) {
        super(driver, pageTitle);
    }

    public StoreSearchPage sortBy(ProductSortedBy sortedBy) {
        sortBtn.click();
        wait.until(ExpectedConditions.attributeContains(sortBtn, "aria-expanded", "true"));
        dropdownMenu.findElement(By.linkText(sortedBy.getLinkText())).click();
        wait.until(ExpectedConditions.textToBePresentInElement(sortBtn, sortedBy.getLinkText()));
        return this;
    }

    private List<ProductSmallContainer> getProductsContainers() {
        return productsWE.stream().map(webElement -> new ProductSmallContainer(webElement)).toList();
    }

    public List<Product> getProducts() {
        return getProductsContainers().stream().map(p -> p.getProduct()).toList();
    }
}
