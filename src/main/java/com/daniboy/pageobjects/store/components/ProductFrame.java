package com.daniboy.pageobjects.store.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductFrame {
    @FindBy(css = ".h1")
    private WebElement title;
    @FindBy(css = "[itemprop='price']")
    private WebElement price;
    @FindBy(css = ".product-variants")
    private WebElement variants;
    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement btnIncreaseQuantity;
    @FindBy(css = ".bootstrap-touchspin-down")
    private WebElement btnDecreaseQuantity;
    @FindBy(css = ".add-to-cart.btn")
    private WebElement btnAddToCart;
    @FindBy(css = ".product-cover")
    private WebElement cover;

    private Product product;
    private WebDriverWait wait;

    public ProductFrame(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(title));
        createProduct();
    }

    private void createProduct() {
        String productName = title.getText();
        String priceStr = price.getText().replace("$", "");
        double productPrice = Double.parseDouble(priceStr);
        this.product = new Product(productName, productPrice);
    }

    public Product getProduct() {
        return product;
    }

    public void clickOnCartBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnAddToCart)).click();
    }//RETORNAR A PAGE DAQUELE MODAL QUE CONTÉM O PRODUCT, PROCEED TO CHECKOUT, CONTINUE SHOPPING... ela aparece apos o clique no add to cart

    //click on increase qtt, etc etc etc

}
