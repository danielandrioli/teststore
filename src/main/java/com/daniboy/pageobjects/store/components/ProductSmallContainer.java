package com.daniboy.pageobjects.store.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
    Classe utilizada na listagem de itens na homepage e na página de busca.
 */
public class ProductSmallContainer {
    private WebElement root;
    private By nameBy = By.cssSelector(".product-title");
    private By priceBy = By.cssSelector(".price");
    private By quickViewBy = By.cssSelector(".quick-view");
    private Product product;

    public ProductSmallContainer(WebElement root) {
        this.root = root;
        String productName = root.findElement(nameBy).getText();
        String priceStr = root.findElement(priceBy).getText().replace("$", "");
        double productPrice = Double.parseDouble(priceStr);
        this.product = new Product(productName, productPrice);
    }

    public Product getProduct() { return product; }

    public void clickOnQuickView(Actions action) { // Quick View aparece apenas após deixar mouse em cima. Sem isso, dá exception.
        action.moveToElement(root).perform(); // moveToElement shifts the mouse pointer to the center of the element
        root.findElement(quickViewBy).click();
    }

    public void clickOnProduct() {
        root.click();
    }
}
