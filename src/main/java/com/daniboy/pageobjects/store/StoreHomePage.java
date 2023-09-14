package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class StoreHomePage extends StoreBasePage {
    public static final String pageTitle = "teststore";
    @FindBy(css = ".product-miniature")
    private List<WebElement> productsWE;
    @FindBy(css = ".add-to-cart.btn.btn-primary")
    private WebElement addToCartBtn;

    public StoreHomePage(WebDriver driver) {
        super(driver, pageTitle);
    }

    public List<Product> getProducts() {
        return productsWE.stream().map(webElement -> new Product(webElement)).toList();
    }

    public Product getProduct(Predicate<Product> condition) {
        return getProducts().stream().filter(condition).findFirst().orElseThrow();
    }

    public void clickOnCartBtn() { //talvez isso deve pertencer a outra classe... e essa classe ser um atributo de StoreHomePage
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn)).click(); // waits deve estar nessa clase (Page class)
    }//RETORNAR A PAGE DAQUELE MODAL QUE CONTÉM O PRODUCT, PROCEED TO CHECKOUT, CONTINUE SHOPPING...

}
