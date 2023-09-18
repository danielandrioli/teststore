package com.daniboy.pageobjects.store.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductFrame {
    @FindBy(css = ".h1")
    private WebElement name;
    @FindBy(css = ".current-price [itemprop]")
    private WebElement price;
//    @FindBy(css = ".product-variants")
//    private WebElement variants;
    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement btnIncreaseQuantity;
    @FindBy(css = ".bootstrap-touchspin-down")
    private WebElement btnDecreaseQuantity;
    @FindBy(css = ".add-to-cart.btn")
    private WebElement btnAddToCart;
    @FindBy(css = ".product-cover")
    private WebElement cover;
    @FindBy(css = "#quantity_wanted")
    private WebElement quantity;

    private Product product;
    private WebDriverWait wait;
    private WebDriver driver;

    public ProductFrame(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(name));
        wait.until(ExpectedConditions.visibilityOf(price));
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

    public ProductAddedToCartFrame clickOnAddToCartBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnAddToCart)).click();
        return new ProductAddedToCartFrame(driver);
    }

    public ProductFrame clickOnIncreaseQuantity() {
        btnIncreaseQuantity.click();
        return this;
    }

    public ProductFrame clickOnDecreaseQuantity() {
        btnDecreaseQuantity.click();
        return this;
    }

    public ProductFrame selectVariant(String variant) {
        Select select = new Select(driver.findElement(By.cssSelector("select"))); // Alguns produtos não tem variante,
        select.selectByVisibleText(variant);                                      // por isso não posso inicializar com findBy.
        return this;
    }


}
