package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductFrame;
import com.daniboy.pageobjects.store.components.ProductSmallContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.Predicate;

public class StoreHomePage extends StoreBasePage {
    public static final String pageTitle = "teststore";
    @FindBy(css = ".product-miniature")
    private List<WebElement> productsWE;
    @FindBy(css = ".add-to-cart.btn.btn-primary")
    private WebElement addToCartBtn;
    private ProductFrame productFrame;

    public StoreHomePage(WebDriver driver) {
        super(driver, pageTitle);
    }

    public List<ProductSmallContainer> getProductsContainers() {
        return productsWE.stream().map(webElement -> new ProductSmallContainer(webElement)).toList();
    }

    private ProductSmallContainer getProductContainer(Predicate<Product> condition) {
        return getProductsContainers().stream().filter(pContainer -> condition.test(pContainer.getProduct()))
                .findFirst().orElseThrow();
    }

//    public Product getProduct() {
//        return productFrame.getProduct();
//    }

    public StoreProductPage clickOnProduct(Predicate<Product> condition) {
        ProductSmallContainer pContainer = getProductContainer(condition);
        pContainer.clickOnProduct();
        return new StoreProductPage(driver, pContainer.getProduct().getName());
    }

    public ProductFrame clickOnQuickView(Predicate<Product> condition) {
        getProductContainer(condition).clickOnQuickView(new Actions(driver));
        productFrame = new ProductFrame(driver);
        return productFrame;
    }

    public void addProductToCart() {//deletar?
//        if (productFrame == null) throw new ClassCastException();
        productFrame.clickOnCartBtn();
    }

//    public void clickOnCartBtn() { //talvez isso deve pertencer a outra classe... e essa classe ser um atributo de StoreHomePage
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        wait.until(ExpectedConditions.visibilityOf(addToCartBtn)).click(); // waits deve estar nessa clase (Page class)
//    }//RETORNAR A PAGE DAQUELE MODAL QUE CONTÉM O PRODUCT, PROCEED TO CHECKOUT, CONTINUE SHOPPING... ela aparece apos o clique no add to cart

}
