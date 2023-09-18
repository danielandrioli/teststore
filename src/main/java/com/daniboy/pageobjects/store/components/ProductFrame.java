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
    private WebElement name;
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
    private WebDriver driver;

    public ProductFrame(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(name));
        this.driver = driver;
        createProduct();
    }

    private void createProduct() {
        String priceStr = price.getText().replace("$", "");
        double productPrice = Double.parseDouble(priceStr);
        this.product = new Product(name.getText(), productPrice);
    }

    public Product getProduct() {
        return product;
    }

    public ProductAddedToCartFrame clickOnCartBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnAddToCart)).click();
        return new ProductAddedToCartFrame(driver);
    }
}
